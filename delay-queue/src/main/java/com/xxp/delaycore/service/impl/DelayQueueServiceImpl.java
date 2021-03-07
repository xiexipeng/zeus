package com.xxp.delaycore.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.xxp.delaycore.constant.DelayQueueConstant;
import com.xxp.delaycore.dto.Job;
import com.xxp.delaycore.service.DelayQueueService;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RMap;
import org.redisson.api.RScoredSortedSet;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: xiexipeng@u51.com
 * @create: 2021/03/07 20:26:09
 * @description: 延迟队列服务
 * @Version V1.0
 **/
@Slf4j
@Service
public class DelayQueueServiceImpl implements DelayQueueService {

    @Autowired
    private RedissonClient redissonClient;

    @Override
    public Boolean addJob(Job job) {
        try {
            RMap<Object, Object> jobMap = redissonClient.getMap(DelayQueueConstant.JOB_POOL_KEY);
            String topicId = job.getTopicId();
            if (jobMap.get(topicId) != null){
                log.warn("job已经存在，请不要重复添加, job:{}", JSONObject.toJSONString(job));
                return false;
            }
            RScoredSortedSet<Object> scoredSortedSet = redissonClient.getScoredSortedSet(DelayQueueConstant.SORTED_JOB_QUEUE_KEY);
            jobMap.put(job.getTopicId(), job);
            scoredSortedSet.add(job.getDelay(), topicId);
            return true;
        } catch (Exception e) {
            log.info("添加job异常, job:{}", JSONObject.toJSONString(job), e);
        }
        return false;
    }

    @Override
    public Boolean deleteJob(String topic, Long jobId) {
        try {
            RMap<Object, Object> jobMap = redissonClient.getMap(DelayQueueConstant.JOB_POOL_KEY);
            RScoredSortedSet<Object> scoredSortedSet = redissonClient.getScoredSortedSet(DelayQueueConstant.SORTED_JOB_QUEUE_KEY);
            String topicId = getTopicId(topic, jobId);
            jobMap.remove(topicId);
            scoredSortedSet.remove(topicId);
            return true;
        } catch (Exception e) {
            log.info("删除job异常, topic:{}, jobId:{}", topic, jobId, e);
        }
        return false;
    }

    private String getTopicId(String topic, Long jobId) {
        return topic + ":" + jobId;
    }
}
