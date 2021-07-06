package com.xxp;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * @author: xiexipeng
 * @create: 2021/04/08 10:58:25
 * @description: redis分布式锁
 * @Version V1.0
 **/
@Slf4j
public class RedisDistributeLock implements DistributeLock {


    public RedisDistributeLock() {
    }

    @Override
    public void lock(String key, long leaseTime, TimeUnit timeUnit) {

    }

    @Override
    public boolean tryLock(String key, long waitTime, long leaseTime, TimeUnit timeUnit) {
        return false;
    }

    @Override
    public boolean tryLockIncr(String key, long waitTime, long interval, long leaseTime, TimeUnit timeUnit) {
        return false;
    }

    @Override
    public boolean isLocked(String key) {
        return false;
    }

    @Override
    public boolean isLocked(String key, long waitTime, TimeUnit timeUnit) {
        return false;
    }

    @Override
    public void unLock(String key) {

    }
}
