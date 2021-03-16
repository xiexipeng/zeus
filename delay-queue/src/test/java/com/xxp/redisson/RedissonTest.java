package com.xxp.redisson;

import com.xxp.BaseTest;
import org.junit.jupiter.api.Test;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author: xiexipeng@u51.com
 * @create: 2021/03/09 19:22:23
 * @description:
 * @Version
 **/
public class RedissonTest extends BaseTest {

    @Autowired
    private RedissonClient client;

    @Test
    public void test(){
        RMap<String, Object> test_map = client.getMap("test_map");
        test_map.put("test","11");
    }
}
