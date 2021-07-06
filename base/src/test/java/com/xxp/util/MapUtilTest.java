package com.xxp.util;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author: xiexipeng
 * @create: 2021/03/18 15:43:40
 * @description:
 * @Version
 **/
public class MapUtilTest {

    public static void main(String[] args) {
        Map<String, String> test = MapUtil.<String, String>createBuilder().put("test", "11").put(11, 22).build();
        System.out.println(test);

        MapUtil.<Object, Object>createBuilder("key", "123").put(11, 22);

//        MapUtil.<String,Object>createBuilder("key","123").put(11,22);

//        MapUtil.createBuilder("key","123").put(11,22);
        MapUtil.createBuilder("key", 123).put("aa", 22);

        Map<String, String> map = new ConcurrentHashMap<>();
//        map.put(21,22);

        MapUtil.MapBuilder<String, String> builder = new MapUtil.MapBuilder<>();
//        builder.put(11,22);
        Map test2 = new ConcurrentHashMap();
        test2.put(11, 33);
        test2.put("aa", "kk");

        MapUtilTest mapUtil = new MapUtilTest();
        mapUtil.<String>test("");
    }

    public  <T> void test(T t) {
        System.out.println(t);
    }
}
