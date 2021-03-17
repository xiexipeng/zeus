package com.xxp.core.task;

import com.xxp.core.dto.Job;

/**
 * @author: xiexipeng@u51.com
 * @create: 2021/03/08 14:22:29
 * @description:
 * @Version
 **/
public interface TaskService<T> {

    T process(Job job);
}
