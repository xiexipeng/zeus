package com.xxp.cluster.register;

import com.xxp.server.Server;

import java.util.List;

/**
 * @author: xiexipeng@u51.com
 * @create: 2021/05/07 19:25:34
 * @description: 注册中心
 * @Version V1.0
 **/
public interface Registry {

    /**
     * 服务注册
     *
     * @param serviceName
     * @param serviceTag
     */
    void registry(String serviceName, String serviceTag);

    /**
     * 服务订阅
     *
     * @param serviceName
     * @param serviceTag
     */
    List<Server> subscribe(String serviceName, String serviceTag);
}
