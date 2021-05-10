package com.xxp.cluster.loadbalance;

import com.xxp.common.URL;
import com.xxp.server.Server;

import java.util.List;
import java.util.Random;

/**
 * @author: xiexipeng@u51.com
 * @create: 2021/04/16 17:40:21
 * @description: 加权随机
 * @Version V1.0
 **/
public class RandomLoadBalance extends AbstractLoadBalance {

    @Override
    public Server doSelect(List<Server> serverList, URL url) {
        // 1.构建权重数组
        int length = serverList.size();
        int[] weigths = new int[length];
        for (int i = 0; i < length; i++) {
            weigths[i] = serverList.get(i).getWeight();
        }
        // 2.判断是否权重全部相等
        boolean isSameWeight = true;
        int firstWeight = serverList.get(0).getWeight();
        for (int i = 0; i < length; i++) {

            if (firstWeight != weigths[i]) {
                isSameWeight = false;
            }
            firstWeight = weigths[i];
        }
        // 3.加权随机
        int totalWeight = serverList.stream().mapToInt(Server::getWeight).sum();
        Random random = new Random();
        int next = random.nextInt(totalWeight);
        if (!isSameWeight) {
            for (int i = 0; i < length; i++) {
                next -= weigths[i];
                if (next < 0) {
                    return serverList.get(i);
                }
            }
        }
        // 4.权重一致直接随机
        return serverList.get(new Random().nextInt(length));
    }
}
