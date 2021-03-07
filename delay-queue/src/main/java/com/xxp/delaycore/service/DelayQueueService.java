package com.xxp.delaycore.service;

import com.xxp.delaycore.dto.Job;

/**
 * @author: xiexipeng@u51.com
 * @create: 2021/03/07 17:57:40
 * @description: 延迟队列服务对象
 * @Version V1.0
 **/
public interface DelayQueueService {

    /**
     * 添加job
     * @param job
     * @return
     */
    Boolean addJob(Job job);

    /**
     * 删除job
     * @param topic
     * @param jobId
     * @return
     */
    Boolean deleteJob(String topic, Long jobId);
}
