package com.xxp.cluster.loadbalance;

import com.xxp.common.URL;
import com.xxp.server.Server;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author: xiexipeng@u51.com
 * @create: 2021/04/19 14:24:58
 * @description: 一致性hash
 * @Version V1.0
 **/
public class ConsistentHashLoadBalancce extends AbstractLoadBalance {

    private final TreeMap<Long, Server> serverTreeMap = new TreeMap<>();
    private final int virtualNumber;

    public ConsistentHashLoadBalancce(List<Server> serverList, int virtualNumber) {
        this.virtualNumber = virtualNumber;
        for (int i = 0; i < serverList.size(); i++) {
            Server server = serverList.get(i);
            String address = server.getHost() + ":" + server.getPort();
            for (int j = 0; j < virtualNumber; j++) {
                // 设置虚拟节点，避免数据倾斜
                serverTreeMap.put(hash(md5(address), virtualNumber), server);
            }
        }
    }

    /**
     * 一致性hash算法目标：1.解决hash算法因为节点数量变动导致的大量数据迁移。2.通过添加虚拟节点避免数据倾斜
     *
     * @param serverList
     * @param url
     * @return
     */
    @Override
    public Server doSelect(List<Server> serverList, URL url) {
        // 默认使用第一个参数作为hash key,后期可以做配置
        String key = url.getArguments()[0].toString();
        long hash = hash(md5(key), 0);
        // 返回大于或等于给定键元素(hash)的最小键元素链接的键值对
        Map.Entry<Long, Server> entry = serverTreeMap.ceilingEntry(hash);
        if (entry == null) {
            entry = serverTreeMap.firstEntry();
        }
        return entry.getValue();
    }

    private long hash(byte[] digest, int number) {
        return (((long) (digest[3 + number * 4] & 0xFF) << 24)
                | ((long) (digest[2 + number * 4] & 0xFF) << 16)
                | ((long) (digest[1 + number * 4] & 0xFF) << 8)
                | (digest[number * 4] & 0xFF))
                & 0xFFFFFFFFL;// hash环最大值Integer.MAX_VALUE的两倍
    }

    private byte[] md5(String value) {
        MessageDigest md5;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException(e.getMessage(), e);
        }
        md5.reset();
        byte[] bytes = value.getBytes(StandardCharsets.UTF_8);
        md5.update(bytes);
        return md5.digest();
    }

}
