package com.xxp.core.schedule;

import com.xxp.core.constant.DelayQueueConstant;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RBlockingQueue;
import org.redisson.api.RLock;
import org.redisson.api.RScoredSortedSet;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author: xiexipeng
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
    public void carryJobSchedule() {
        RLock lock = redissonClient.getLock(DelayQueueConstant.SCHEDULE_DISTRIBUTE_LOCK_KEY);
        boolean isLock = false;
        try {
            isLock = lock.tryLock(DelayQueueConstant.LOCK_WAIT_TIME, DelayQueueConstant.SCHEDULE_LOCK_LEASE_TIME, TimeUnit.SECONDS);
            if (!isLock) {
                return;
            }
            long currentTimeMillis = System.currentTimeMillis();
            // (0,now]
            RScoredSortedSet<String> scoredSortedSet = redissonClient.getScoredSortedSet(DelayQueueConstant.SORTED_JOB_QUEUE_KEY);
            Collection<String> objects = scoredSortedSet.valueRange(0, false, currentTimeMillis, true);
            if (CollectionUtils.isEmpty(objects)){
                return;
            }
            List<String> list = objects.stream().map(String::valueOf).collect(Collectors.toList());
            RBlockingQueue<String> blockingQueue = redissonClient.getBlockingQueue(DelayQueueConstant.PRE_JOB_QUEUE_KEY);
            blockingQueue.addAll(list);
            // TODO 如果此时程序崩溃，job已经被添加到处理池中被处理，下次重启将会再次添加这个任务到处理池中，出现一个任务被调度多次到情况
            scoredSortedSet.removeAllAsync(list);
        } catch (Exception e) {
            log.warn("延迟队列调度异常", e);
        } finally {
            if (lock != null && isLock) {
                lock.unlock();
            }
        }
    }

}
