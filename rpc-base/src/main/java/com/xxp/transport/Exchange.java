package com.xxp.transport;

import com.xxp.common.URL;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author: xiexipeng
 * @create: 2021/04/20 10:42:49
 * @description: 远程服务调用转换
 * @Version V1.0
 **/
public class Exchange {

    // 远程服务的客户端 key = ip:port
    private ConcurrentHashMap<String, NettyInvoker> referClient = new ConcurrentHashMap<>();

    /**
     * 暴露服务
     */
    public void export() {

    }

    /**
     * 服务引用
     */
    public void reply(URL url) {
        referClient.computeIfAbsent(url.getAddress(), u -> new NettyInvoker(new NettyClient(url)));
    }

}
