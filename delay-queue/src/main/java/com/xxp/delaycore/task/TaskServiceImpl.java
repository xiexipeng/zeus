package com.xxp.delaycore.task;

import com.xxp.delaycore.constant.DelayQueueConstant;
import com.xxp.delaycore.dto.Job;
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
        log.info("job任务执行完毕, topicId:[{}]", job.getTopicId());
        return true;
    }

}
