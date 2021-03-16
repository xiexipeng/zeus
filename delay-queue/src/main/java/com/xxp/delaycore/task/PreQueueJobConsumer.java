package com.xxp.delaycore.task;

import com.alibaba.fastjson.JSONObject;
import com.xxp.delaycore.constant.DelayQueueConstant;
import com.xxp.delaycore.dto.Job;
import com.xxp.delaycore.exception.DelayQueueRetryException;
import jodd.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.*;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Map;
import java.util.concurrent.*;

/**
 * @author: xiexipeng@u51.com
 * @create: 2021/03/08 14:02:59
 * @description: 代执行job消费者执行器
 * @Version V1.0
 **/
@Slf4j
@Component
public class PreQueueJobConsumer implements InitializingBean {

    @Autowired
    private RedissonClient redissonClient;

    @Autowired
    private Map<String, TaskService> taskServiceMap;

    private static final ThreadFactory threadFactory = new ThreadFactoryBuilder()
            .setNameFormat("delay-job-consumer-thread-%d")
            .setDaemon(true)
            .get();
    private static final BlockingQueue<Runnable> blockingQueue = new LinkedBlockingQueue<Runnable>(100);
    private static ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 10, 30L, TimeUnit.SECONDS, blockingQueue, threadFactory, new ThreadPoolExecutor.AbortPolicy());

    @Override
    public void afterPropertiesSet() {
        log.info("延迟任务消费者线程启动");
        new Thread("Topic_Consumer_Thread") {
            @Override
            public void run() {
                consumer();
            }
        }.start();
    }

    private void consumer() {
        while (true) {
            RLock lock = null;
            boolean isLock = false;
            try {
                lock = redissonClient.getLock(DelayQueueConstant.CONSUMER_DISTRIBUTE_LOCK_KEY);
                // 锁超时时间需要大于阻塞队列poll的阻塞时间
                isLock = lock.tryLock(DelayQueueConstant.LOCK_WAIT_TIME, DelayQueueConstant.CONSUMER_LOCK_LEASE_TIME, TimeUnit.SECONDS);
                if (!isLock) {
                    continue;
                }
                doConsumer();
            } catch (Exception e) {
                log.warn("job消费者执行异常", e);
            } finally {
                if (lock != null && isLock) {
                    try {
                        lock.unlock();
                    } catch (Exception e) {
                        log.warn("释放锁异常", e);
                    }
                }
            }
        }
    }

    private void doConsumer() throws InterruptedException {
        RBlockingQueue<String> blockingQueue = redissonClient.getBlockingQueue(DelayQueueConstant.PRE_JOB_QUEUE_KEY);
        String topicId = blockingQueue.pollLastAndOfferFirstTo(DelayQueueConstant.PRE_JOB_BACK_QUEUEU_KEY, 60, TimeUnit.SECONDS);
        if (StringUtils.isEmpty(topicId)) {
            log.info("获取topicid为空");
            return;
        }
        RMap<String, Job> jobPool = redissonClient.getMap(DelayQueueConstant.JOB_POOL_KEY);
        Job job = jobPool.get(topicId);
        CompletableFuture.supplyAsync(() -> taskServiceMap.get(job.getTopic()).process(job), threadPoolExecutor).handle((v, e) -> {
            if (e != null && e instanceof DelayQueueRetryException) {
                Integer retry = job.getRetry();
                if (retry > job.getMaxRetry()) {
                    log.warn("job任务重试次数超限，已经强制结束");
                    // TODO save to DB
                    return false;
                }
                job.setRetry(++retry);
                RScoredSortedSet<Object> scoredSortedSet = redissonClient.getScoredSortedSet(DelayQueueConstant.SORTED_JOB_QUEUE_KEY);
                scoredSortedSet.add(System.currentTimeMillis() + job.getRetryInterval(), topicId);
                jobPool.put(topicId, job);
                return false;
            }
            if (e != null && e instanceof Exception) {
                log.warn("job任务执行异常, job:{}", JSONObject.toJSONString(job), e);
                // TODO save to DB
                return false;
            }
            jobPool.remove(topicId);
            RQueue<String> queue = redissonClient.getQueue(DelayQueueConstant.PRE_JOB_BACK_QUEUEU_KEY);
            queue.remove(topicId);
            return true;
        });
    }

    public static void main(String[] args) {
        int i = 1;
        while (i < 5) {
            i = i + 1;
            System.out.println(i);
            try {
                if (i > 3) {
                    System.out.println("....");
                    continue;
                }
                System.out.println("<>>>>");
            } catch (Exception e) {

            } finally {
                System.out.println("/////");
            }
        }
    }
}
