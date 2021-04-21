package com.xxp;

import java.util.concurrent.TimeUnit;

/**
 * @author: xiexipeng
 * @create: 2021/04/07 19:17:10
 * @description: 分布式锁
 * @Version
 **/
public interface DistributeLock {

    /**
     * 获取分布式锁，并设置锁超时时间，如果获取锁失败，则等待上一个锁超时后重试
     *
     * @param key
     * @param leaseTime
     * @param timeUnit
     */
    void lock(String key, long leaseTime, TimeUnit timeUnit);

    /**
     * 尝试获取锁，并设置锁超时时间，如果获取锁失败，则等待一段时间重试
     * 支持重入锁、未设置锁超时时间时支持锁超时自动续期
     *
     * @param key
     * @param waitTime
     * @param leaseTime
     * @param timeUnit
     * @return
     */
    boolean tryLock(String key, long waitTime, long leaseTime, TimeUnit timeUnit);

    /**
     * 尝试获取锁，并设置锁超时时间，如果获取锁失败，则等待一段时间按一定时间间隔重试
     *
     * @param key
     * @param waitTime
     * @param interval
     * @param leaseTime
     * @param timeUnit
     * @return
     */
    boolean tryLockIncr(String key, long waitTime, long interval, long leaseTime, TimeUnit timeUnit);

    /**
     * 是否已经加锁
     *
     * @param key
     * @return
     */
    boolean isLocked(String key);

    /**
     * 是否已经加锁，如果已经有锁，等待一段时间重新判断是否加锁
     *
     * @param key
     * @param waitTime
     * @param timeUnit
     * @return
     */
    boolean isLocked(String key, long waitTime, TimeUnit timeUnit);

    /**
     * 释放锁
     *
     * @param key
     */
    void unLock(String key);
}
