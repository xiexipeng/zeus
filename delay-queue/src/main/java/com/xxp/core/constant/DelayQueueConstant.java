package com.xxp.core.constant;

/**
 * @author: xiexipeng@u51.com
 * @create: 2021/03/07 20:28:58
 * @description: 延迟队列常量
 * @Version V1.0
 **/
public class DelayQueueConstant {

    /**
     * redis有序集key，按照任务延迟时间排序，保存任务唯一标识topicId
     */
    public final static String SORTED_JOB_QUEUE_KEY = "SORTED_JOB_QUEUE";

    /**
     * 任务池key，redis的map集合，保存任务topicId与其关联的job
     */
    public final static String JOB_POOL_KEY = "TOPIC_JOB_POOL";

    /**
     * 等待执行的任务队列key，是一个阻塞队列，队列为空的时候获取任务将会被阻塞
     */
    public final static String PRE_JOB_QUEUE_KEY = "PRE_JOB_QUEUE";

    /**
     * 等待执行任务队列备份，当任务从等待执行队列中取出后，系统宕机，任务可能出现丢失，因此使用BRPOPLPUSH命令将任务进行备份
     */
    public final static String PRE_JOB_BACK_QUEUEU_KEY = "PRE_JOB_BACK_QUEUE";

    /**
     * 调度器分布式锁
     */
    public final static String SCHEDULE_DISTRIBUTE_LOCK_KEY = "DELAY_JOB_LOCK_KEY";

    /**
     * 任务消费者分布式锁
     */
    public final static String CONSUMER_DISTRIBUTE_LOCK_KEY = "CONSUMER_JOB_LOCK_KEY";

    /**
     * 分布式锁等待时间，单位秒
     */
    public final static Integer LOCK_WAIT_TIME = 3;

    /**
     * 调度器分布式锁失效时间，单位秒
     */
    public final static Integer SCHEDULE_LOCK_LEASE_TIME = 60;

    /**
     * 消费者分布式锁失效时间，单位秒
     */
    public final static Integer CONSUMER_LOCK_LEASE_TIME = 80;

    /**
     * 测试topic
     */
    public final static String TEST_TOPIC = "test_topic";
}
