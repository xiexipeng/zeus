package com.xxp.service;

import com.xxp.cluster.register.Registry;
import com.xxp.proxy.JdkProxyFactory;
import com.xxp.proxy.ProxyFactory;
import com.xxp.server.Server;
import com.xxp.spi.ExtensionLoader;

import java.util.List;
import java.util.Map;

/**
 * @author: xiexipeng
 * @create: 2021/05/04 15:59:12
 * @description: 远程服务代理创建工厂类
 * @Version V1.0
 **/
public class BaseClient<T> {

    private Class<T> tClass;
    private ReferenceConfig referenceConfig;

    private final Registry registry;

    public BaseClient(String serviceName, String serviceTag) {
        this(new ReferenceConfig(serviceName, serviceTag));
    }

    public BaseClient(ReferenceConfig referenceConfig) {
        this.referenceConfig = referenceConfig;
        this.registry = ExtensionLoader.getExtensionLoader(Registry.class).getInstant(referenceConfig.getString(ReferenceConfig.REGISTRY_CLASS_CONFIG));
    }

    /**
     * 创建默认配置远程服务代理
     *
     * @param tClass
     * @param <T>
     * @return
     */
    public <T> T createClient(Class<T> tClass) {
        List<Server> subscribe = registry.subscribe(referenceConfig.getString(ReferenceConfig.SERVICE_NAME_CONFIG), referenceConfig.getString(ReferenceConfig.SERVICE_TAG_CONFIG));
        ProxyFactory proxyFactory = new JdkProxyFactory();
        return proxyFactory.getProxy(tClass,null,null);
    }
}
