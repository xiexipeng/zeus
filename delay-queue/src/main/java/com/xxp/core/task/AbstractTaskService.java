package com.xxp.core.task;

import com.xxp.core.dto.Job;
import com.xxp.core.util.SpendTimeLogSuit;

/**
 * @author: xiexipeng@u51.com
 * @create: 2021/03/16 16:20:57
 * @description: 任务抽象服务
 * @Version V1.0
 **/
public abstract class AbstractTaskService<T> implements TaskService {

    @Override
    public T process(Job job) {
        T resutl = SpendTimeLogSuit.wrap(() -> doProcess(job), "Delay_Task_Job_" + job.getTopicId());
        return resutl;
    }

    protected abstract T doProcess(Job job);
}
