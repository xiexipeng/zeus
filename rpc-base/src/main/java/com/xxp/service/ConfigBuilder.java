package com.xxp.service;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: xiexipeng@u51.com
 * @create: 2021/05/05 11:28:10
 * @description:
 * @Version
 **/
public class ConfigBuilder {

    private Map<String, Config> configs = new HashMap<>();

    public ConfigBuilder build(String key, Object defaultValue) {
        this.configs.put(key, new Config(key, null, defaultValue, false));
        return this;
    }

    public ConfigBuilder build(String key, Object value, boolean isMust) {
        this.configs.put(key, new Config(key, value, value, isMust));
        return this;
    }

    public ConfigBuilder build(String key, Object value, Object defaultValue, boolean isMust) {
        this.configs.put(key, new Config(key, value, defaultValue, isMust));
        return this;
    }

    public ConfigBuilder parse(Map<String ,Object> configMap){
        if (configMap == null){
            return this;
        }
        configMap.entrySet().forEach(e->{
            Config config = configs.get(e.getKey());
            if (config == null){
                return;
            }
            config.setValue(e.getValue());
        });
        return this;
    }

    public Map<String, Config> getConfigs() {
        return configs;
    }

    @Data
    public static class Config {
        private String name;
        private Object value;
        private Object defaultValue;
        private boolean isMust;
        private Class configType;

        public Config(String name, Object value, Object defaultValue, boolean isMust) {
            this.name = name;
            this.value = value;
            this.defaultValue = defaultValue;
            this.isMust = isMust;
        }
    }
}
