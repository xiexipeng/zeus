package com.xxp.delaycore.constant;

/**
 * @author: xiexipeng@u51.com
 * @create: 2021/03/07 20:28:58
 * @description: 延迟队列常量
 * @Version V1.0
 **/
public class DelayQueueConstant {

    public final static String SORTED_JOB_QUEUE_KEY = "SORTED_JOB_QUEUE";

    public final static String JOB_POOL_KEY ="TOPIC_JOB_POOL";

    public final static String PRE_JOB_QUEUE_KEY = "PRE_JOB_QUEUE";

    public final static String DISTRIBUTE_LOCK_KEY = "DELAY_JOB_LOCK_KEY";

    public final static Integer LOCK_WAIT_TIME = 3;

    public final static Integer LOCK_LEASE_TIME = 61;
}
