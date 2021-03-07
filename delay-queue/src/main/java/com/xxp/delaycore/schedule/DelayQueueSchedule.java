package com.xxp.delaycore.schedule;

import com.xxp.delaycore.constant.DelayQueueConstant;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RBlockingQueue;
import org.redisson.api.RLock;
import org.redisson.api.RScoredSortedSet;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author: xiexipeng@u51.com
 * @create: 2021/03/07 21:14:08
 * @description: 延迟队列调度器
 * @Version V1.0
 **/
@Slf4j
@Component
public class DelayQueueSchedule {

    @Autowired
    private RedissonClient redissonClient;

    /**
     * 获取延迟队列里的topicId
     */
    @Scheduled(cron = "*/1 * * * * *")
    public void carryJobSchduel() {
        RScoredSortedSet<Object> scoredSortedSet = redissonClient.getScoredSortedSet(DelayQueueConstant.SORTED_JOB_QUEUE_KEY);
        RLock lock = redissonClient.getLock(DelayQueueConstant.DISTRIBUTE_LOCK_KEY);
        try {
            boolean tryLock = lock.tryLock(DelayQueueConstant.LOCK_WAIT_TIME, DelayQueueConstant.LOCK_LEASE_TIME, TimeUnit.SECONDS);
            if (!tryLock) {
                return;
            }
            long currentTimeMillis = System.currentTimeMillis();
            // (0,now]
            Collection<Object> objects = scoredSortedSet.valueRange(0, false, currentTimeMillis, true);
            List<String> list = objects.stream().map(String::valueOf).collect(Collectors.toList());
            RBlockingQueue<Object> blockingQueue = redissonClient.getBlockingQueue(DelayQueueConstant.PRE_JOB_QUEUE_KEY);
            blockingQueue.addAll(list);
            scoredSortedSet.removeAllAsync(list);
        } catch (Exception e) {
            log.warn("延迟队列调度异常", e);
        } finally {
            if (lock != null) {
                lock.unlock();
            }
        }
    }
}
