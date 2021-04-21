package com.xxp.client.loadbalance;

import com.xxp.common.URL;
import com.xxp.server.Server;

import java.util.List;
import java.util.TreeMap;

/**
 * @author: xiexipeng@u51.com
 * @create: 2021/04/19 14:24:58
 * @description: 一致性hash
 * @Version V1.0
 **/
public class ConsistentHashLoadBalancce extends AbstractLoadBalance {

    private TreeMap<String,Server> serverTreeMap = new TreeMap<>();
    private int virtualNumber;

    public ConsistentHashLoadBalancce(List<Server> serverList, int virtualNumber) {
        this.virtualNumber = virtualNumber;
    }

    /**
     * 一致性hash算法目标：1.解决hash算法因为节点数量变动导致的大量数据迁移。2.通过添加虚拟节点避免数据倾斜
     * @param serverList
     * @param url
     * @return
     */
    @Override
    public Server doSelect(List<Server> serverList, URL url) {
        return null;
    }

    private long hash(Object key){
        return 0L;
    }
}
