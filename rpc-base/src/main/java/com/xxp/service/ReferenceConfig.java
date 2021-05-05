package com.xxp.service;

import java.util.Map;

/**
 * @author: xiexipeng@u51.com
 * @create: 2021/05/04 15:48:40
 * @description: 服务引用配置，参考Kafka配置设计
 * @Version V1.0
 **/
public class ReferenceConfig extends AbstractConfig {

    private static ConfigBuilder configBuilder;

    private static final String PROXY_CLASS_CONFIG = "proxy.class";

    // 设置默认配置
    static {
        configBuilder = new ConfigBuilder().build(PROXY_CLASS_CONFIG, "javassist");
    }

    public ReferenceConfig() {
        super(configBuilder);
    }

    public ReferenceConfig(Map<String,Object> configs){
        super(configBuilder.parse(configs));
    }
}
