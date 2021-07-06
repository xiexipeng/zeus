package com.xxp.core.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Map;

/**
 * @author: xiexipeng
 * @create: 2021/03/07 18:19:45
 * @description: 延迟任务
 * @Version V1.0
 **/
@Data
public class Job implements Serializable {

    private static final long serialVersionUID = 1L;

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

    /**
     * 任务下次执行时间
     */
    private Long nextExecuteTime;

    /**
     * 任务执行超时时间,默认60秒,备份任务队列中的任务超过此超时时间还未执行完，则执行补偿任务
     */
    private Long processTimeOut = 60000L;

    /**
     * 任务创建时间
     */
    private Long createTime = System.currentTimeMillis();

    public String getTopicId() {
        return this.topic + ":" + this.jobId;
    }

}
