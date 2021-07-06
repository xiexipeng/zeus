package com.xxp.service;

import com.xxp.BaseTest;
import com.xxp.core.constant.DelayQueueConstant;
import com.xxp.core.dto.Job;
import com.xxp.core.service.DelayQueueService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.*;

/**
 * @author: xiexipeng
 * @create: 2021/03/16 16:14:50
 * @description: 延迟队列测试用例
 * @Version V1.0
 **/
public class DelayQueueTest extends BaseTest {

    @Autowired
    private DelayQueueService delayQueueService;

    @Test
    public void testAddJob() {
        Job job = new Job();
        job.setDelay(30000L);
        job.setJobId(114L);
        job.setMaxRetry(3);
        job.setParam(new HashMap<>());
        job.setTopic(DelayQueueConstant.TEST_TOPIC);
        job.setRetryInterval(30000L);
        job.setNextExecuteTime(job.getDelay() + job.getCreateTime());
        Boolean result = delayQueueService.addJob(job);
        assert result;
    }

    @Test
    public void testConcurrentAdd() throws InterruptedException {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(30, 30, 30000L, TimeUnit.MICROSECONDS, new ArrayBlockingQueue(100));
        CyclicBarrier cyclicBarrier = new CyclicBarrier(10);
        for (int i = 0; i < 10; i++) {
            executor.execute(() -> {
                Job job = createJob();
                try {
                    cyclicBarrier.await();
                    delayQueueService.addJob(job);
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            });

        }
        executor.shutdown();
        Thread.sleep(2000);
    }

    private Job createJob() {
        Random random = new Random();
        Job job = new Job();
        job.setDelay(10000L + random.nextInt(20) * 1000);
        job.setJobId(111L + random.nextInt(100));
        job.setMaxRetry(3);
        job.setParam(new HashMap<>());
        job.setTopic(DelayQueueConstant.TEST_TOPIC);
        job.setRetryInterval(30000L);
        job.setNextExecuteTime(job.getDelay() + job.getCreateTime());
        return job;
    }

}
