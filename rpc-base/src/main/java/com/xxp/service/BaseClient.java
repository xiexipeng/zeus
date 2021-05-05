package com.xxp.service;

import com.xxp.proxy.JdkProxyFactory;
import com.xxp.proxy.ProxyFactory;

import java.util.Map;

/**
 * @author: xiexipeng@u51.com
 * @create: 2021/05/04 15:59:12
 * @description: 远程服务代理创建工厂类
 * @Version V1.0
 **/
public class BaseClient {

    /**
     * 创建默认配置远程服务代理
     *
     * @param tclass
     * @param <T>
     * @return
     */
    public static <T> T createClient(Class<T> tclass) {
        return createClient(tclass, null);
    }

    public static <T> T createClient(Class<T> tclass, Map<String, Object> config) {
        ReferenceConfig referenceConfig = new ReferenceConfig(config);
        ProxyFactory proxyFactory = new JdkProxyFactory();
        return null;
    }
}
