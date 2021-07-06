package com.xxp.cluster.loadbalance;

import com.xxp.common.URL;
import com.xxp.server.Server;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: xiexipeng
 * @create: 2021/04/19 11:32:03
 * @description: 轮训
 * @Version V1.0
 **/
public class RoundLoadBalance extends AbstractLoadBalance {

    private AtomicInteger nextIndex = new AtomicInteger(0);

    @Override
    public Server doSelect(List<Server> serverList, URL url) {
        int index = nextIndex.getAndDecrement() % serverList.size();
        return serverList.get(index);
    }
}
