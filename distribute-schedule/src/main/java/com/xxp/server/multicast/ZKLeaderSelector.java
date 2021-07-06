package com.xxp.server.multicast;

import com.xxp.server.schedule.TimeSchedule;
import com.xxp.server.util.SocketUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.TreeCache;
import org.apache.curator.framework.recipes.cache.TreeCacheEvent;
import org.apache.curator.framework.recipes.cache.TreeCacheListener;
import org.apache.curator.framework.recipes.leader.LeaderLatch;
import org.apache.curator.retry.RetryForever;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.net.SocketException;

/**
 * @author: xiexipeng
 * @create: 2021/03/28 14:32:57
 * @description: zookeeper选主
 * @Version V1.0
 **/
@Slf4j
@Component
public class ZKLeaderSelector {

    private CuratorFramework zkClient;
    private String host;

    private final static String LEADER_ROOT_PATH = "/leader";
    private final static String LEADER_SELECTOR_PATH = LEADER_ROOT_PATH + "/selector";
    private final static String LEADER_HOST_PATH = LEADER_ROOT_PATH + "/host";

    @Autowired
    private TimeSchedule timeSchedule;

    @PostConstruct
    public void init() throws Exception {
        // 1.创建zk客户端
        createCuratorClient();
        // 2.开始选举，选举master失败的非主节点将会阻塞
        startLeaderLatch();
        // 3.添加master节点断开监听器，网络问题断开可以重新参与选举
        addLeaderLoseListener();
        // 4.保存master节点ip
        saveLeaderHost();
        timeSchedule.start();
    }

    private void createCuratorClient() throws SocketException {
        // TODO set connect
        zkClient = CuratorFrameworkFactory.newClient("", new RetryForever(3000));
        host = SocketUtils.getLocalIp();
    }

    private void startLeaderLatch() {
        try (LeaderLatch leaderLatch = new LeaderLatch(zkClient, LEADER_SELECTOR_PATH)) {
            leaderLatch.start();
            // 选举失败的非主节点将会一直阻塞，主节点下线重新选主
            leaderLatch.await();
        } catch (Exception e) {
            log.warn("leader latch 构建失败", e);
            throw new RuntimeException("leader latch 构建失败");
        }
    }

    private boolean hasLeader() throws Exception {
        return null != zkClient.checkExists().forPath(LEADER_HOST_PATH);
    }

    private String getLeaderHost() throws Exception {
        return new String(zkClient.getData().forPath(LEADER_HOST_PATH));
    }

    private void addLeaderLoseListener() throws Exception {
        TreeCache leaderNodeTreeCache = new TreeCache(zkClient, LEADER_HOST_PATH);
        leaderNodeTreeCache.start();
        leaderNodeTreeCache.getListenable().addListener(new TreeCacheListener() {
            @Override
            public void childEvent(CuratorFramework client, TreeCacheEvent event) throws Exception {
                switch (event.getType()) {
                    case NODE_ADDED:
                        break;
                    case NODE_REMOVED:
                        onNodeRemoved(client, event);
                        break;
                    case NODE_UPDATED:
                        break;
                    default:
                        return;
                }
            }

            protected void onNodeRemoved(CuratorFramework client, TreeCacheEvent event) throws Exception {
                String path = event.getData().getPath();
                // TODO 校验event.getData().getData()返回的是当前节点也就是master节点被移除的信息
                String leaderHost = new String(event.getData().getData());
                if (host.equals(leaderHost) && LEADER_HOST_PATH.equals(path)) {
                    log.info("leader node lose");
                    try {
                        timeSchedule.shoutDown();
                        startLeaderLatch();
                    } catch (Exception e) {
                        log.error("reLeaderElect exception ...", e);
                    }
                }
            }

        });
    }

    private void saveLeaderHost() throws Exception {
        if (!hasLeader()){
            zkClient.create().creatingParentContainersIfNeeded()
                    .withMode(CreateMode.EPHEMERAL)
                    .withACL(ZooDefs.Ids.OPEN_ACL_UNSAFE)
                    .forPath(LEADER_HOST_PATH, SocketUtils.getLocalIp().getBytes());
        }
    }
}
