package com.xxp.util;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author: xiexipeng@u51.com
 * @create: 2020/09/28 21:35:55
 * @description: map工具类
 * @Version V1.0
 **/
@Slf4j
public class MapUtil {

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

    public static <T> T mapToObject(Map map, Class<T> tClass) {
        return JSONObject.parseObject(JSONObject.toJSONString(map), tClass);
    }

    public static void main(String[] args) {
        Map<String, String> test = MapUtil.<String, String>createBuilder().put("test", "11").put(11, 22).build();
        System.out.println(test);

        MapUtil.<Object, Object>createBuilder("key", "123").put(11, 22);

//        MapUtil.<String,Object>createBuilder("key","123").put(11,22);

//        MapUtil.createBuilder("key","123").put(11,22);
        MapUtil.createBuilder("key", 123).put("aa", 22);

        Map<String, String> map = new ConcurrentHashMap<>();
//        map.put(21,22);

        MapBuilder<String, String> builder = new MapBuilder<>();
//        builder.put(11,22);
        Map test2 = new ConcurrentHashMap();
        test2.put(11, 33);
        test2.put("aa", "kk");

        MapUtil mapUtil = new MapUtil();
        mapUtil.<String>test("");
    }

    public <T> void test(T t) {
        System.out.println(t);
    }
}
