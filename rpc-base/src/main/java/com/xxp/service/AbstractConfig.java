package com.xxp.service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: xiexipeng@u51.com
 * @create: 2021/05/05 11:58:08
 * @description:
 * @Version
 **/
public class AbstractConfig {

    private Map<String, Object> config = new HashMap<>();

    public AbstractConfig(ConfigBuilder configBuilder) {
        Map<String, ConfigBuilder.Config> configs = configBuilder.getConfigs();
        configs.entrySet().forEach(e -> {
            ConfigBuilder.Config value = e.getValue();
            if (value.getValue() != null) {
                config.put(value.getName(), value.getValue());
                return;
            } else if (!value.isMust()) {
                config.put(value.getName(), value.getDefaultValue());
                return;
            } else {
                throw new RuntimeException("缺少必须配置, key:" + value.getName());
            }
        });
    }

    public int getInt(String name) {
        return (int) config.get(name);
    }

    public String getString(String name) {
        return (String) config.get(name);
    }
}
