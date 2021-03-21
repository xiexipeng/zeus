package com.xxp.util;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author: xiexipeng
 * @create: 2020/09/28 21:35:55
 * @description: map工具类
 * @Version V1.0
 **/
@Slf4j
public class MapUtil {

    /**
     * map构造器
     * @param <K>
     * @param <V>
     */
    public static class MapBuilder<K, V> {

        private Map<K, V> map = new ConcurrentHashMap<>();

        public MapBuilder put(K k, V v) {
            map.put(k, v);
            return this;
        }

        public Map build() {
            return map;
        }
    }

    public static <K, V> MapBuilder<K, V> createBuilder() {
        return new MapBuilder();
    }


    public static <K, V> MapBuilder<K, V> createBuilder(K k, V v) {
        return new MapBuilder<K, V>().put(k, v);
    }

    /**
     * map转对象
     * @param map
     * @param t
     * @param <T>
     * @return
     */
    public static <T> T mapToObject(Map map, T t) {
        try {
            BeanUtils.populate(t,map);
            return t;
        } catch (IllegalAccessException e) {
            log.error("map transfer error, map:{}, object:{}",map,t,e);
        } catch (InvocationTargetException e) {
            log.error("map transfer error, map:{}, object:{}",map,t,e);
        }
        return null;
    }

    /**
     * map转对象
     * @param map
     * @param tClass
     * @param <T>
     * @return
     */
    public static <T> T mapToObject(Map map, Class<T> tClass) {
        return JSONObject.parseObject(JSONObject.toJSONString(map), tClass);
    }
}
