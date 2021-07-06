package com.xxp.service;

import java.util.Map;

/**
 * @author: xiexipeng
 * @create: 2021/05/04 15:48:40
 * @description: 服务引用配置，参考Kafka配置设计
 * @Version V1.0
 **/
public class ReferenceConfig extends AbstractConfig {

    private static ConfigBuilder configBuilder;

    public static final String SERVICE_NAME_CONFIG = "service.name";
    public static final String SERVICE_TAG_CONFIG = "service.tag";

    public static final String PROXY_CLASS_CONFIG = "proxy.class";
    public static final String REGISTRY_CLASS_CONFIG = "registry.class";

    // 设置默认配置
    static {
        configBuilder = new ConfigBuilder().build(PROXY_CLASS_CONFIG, "javassist")
                .build(REGISTRY_CLASS_CONFIG,"ZookeeperRegistry");
    }

    public ReferenceConfig(String serviceName,String serviceTag){
        super(configBuilder.build(SERVICE_NAME_CONFIG,serviceName).build(SERVICE_TAG_CONFIG,serviceTag));
    }

    public ReferenceConfig(Map<String,Object> configs){
        super(configBuilder.parse(configs));
    }
}
