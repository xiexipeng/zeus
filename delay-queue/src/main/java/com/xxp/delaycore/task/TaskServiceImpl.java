package com.xxp.delaycore.task;

import com.xxp.delaycore.dto.Job;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author: xiexipeng@u51.com
 * @create: 2021/03/08 16:45:03
 * @description:
 * @Version
 **/
@Slf4j
@Component("test_topic")
public class TaskServiceImpl implements TaskService<Boolean> {

    @Autowired
    private RedissonClient redissonClient;

    @Override
    public Boolean doTask(Job job) {
        return true;
    }

}
