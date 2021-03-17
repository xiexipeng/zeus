package com.xxp.core.task;

import com.xxp.core.constant.DelayQueueConstant;
import com.xxp.core.dto.Job;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author: xiexipeng@u51.com
 * @create: 2021/03/08 16:45:03
 * @description:
 * @Version
 **/
@Slf4j
@Component(DelayQueueConstant.TEST_TOPIC)
public class TaskServiceImpl extends AbstractTaskService<Boolean> {

    @Override
    public Boolean doProcess(Job job) {
        // TODO 添加任务执行逻辑
//        log.info("job任务执行完毕, topicId:[{}]", job.getTopicId());
        log.info("job执行时效比较,{}ms", System.currentTimeMillis() - job.getNextExecuteTime());
        return true;
    }

}
