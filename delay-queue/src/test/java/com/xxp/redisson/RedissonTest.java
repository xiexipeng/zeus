package com.xxp.redisson;

import com.xxp.BaseTest;
import com.xxp.core.dto.Job;
import org.junit.jupiter.api.Test;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author: xiexipeng
 * @create: 2021/03/09 19:22:23
 * @description:
 * @Version
 **/
public class RedissonTest extends BaseTest {

    @Autowired
    private RedissonClient client;

    @Test
    public void test(){
        RMap<String, Job> test_map = client.getMap("test_map");
        Job job = new Job();
        job.setJobId(146L);
        test_map.put("test",job);
    }

    @Test
    public void testGet(){
        RMap<String, Job> map = client.getMap("test_map");
        Job test = map.get("test");
        System.out.println(test);
        assert test.getJobId() == 146L;
    }
}
