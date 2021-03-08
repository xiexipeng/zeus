package com.xxp.delaycore.task;

import com.xxp.delaycore.dto.Job;

/**
 * @author: xiexipeng@u51.com
 * @create: 2021/03/08 14:22:29
 * @description:
 * @Version
 **/
public interface TaskService<T> {

    T doTask(Job job);
}
