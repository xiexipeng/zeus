package com.xxp.service;

import com.xxp.BaseTest;
import com.xxp.delaycore.constant.DelayQueueConstant;
import com.xxp.delaycore.dto.Job;
import com.xxp.delaycore.service.DelayQueueService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;

/**
 * @author: xiexipeng@u51.com
 * @create: 2021/03/16 16:14:50
 * @description: 延迟队列测试用例
 * @Version V1.0
 **/
public class DelayQueueTest extends BaseTest {

    @Autowired
    private DelayQueueService delayQueueService;

    @Test
    public void testAddJob(){
        Job job = new Job();
        job.setDelay(10000L);
        job.setJobId(112L);
        job.setMaxRetry(3);
        job.setParam(new HashMap<>());
        job.setTopic(DelayQueueConstant.TEST_TOPIC);
        job.setRetryInterval(30000L);
        Boolean result = delayQueueService.addJob(job);
        assert result;
    }
}
