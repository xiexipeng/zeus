package com.xxp.delaycore.dto;

import lombok.Data;

import java.util.Map;

/**
 * @author: xiexipeng@u51.com
 * @create: 2021/03/07 18:19:45
 * @description: 延迟任务
 * @Version V1.0
 **/
@Data
public class Job {

    /**
     * jobID，标示唯一一个延迟任务
     */
    private Long jobId;

    /**
     * job的业务标示
     */
    private String topic;

    /**
     * 延迟时间，单位毫秒
     */
    private Long delay;

    /**
     * job执行参数
     */
    private Map<String, Object> param;

    /**
     * job异常执行的重试次数
     */
    private Integer retry = 0;

    /**
     * 最大重试次数
     */
    private Integer maxRetry;

    /**
     * job异常执行的重试时间间隔
     */
    private Long retryInterval;

    public String getTopicId() {
        return this.topic + ":" + this.jobId;
    }

}
