package com.xxp.lock;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: xiexipeng@u51.com
 * @create: 2021/04/26 17:53:31
 * @description:
 * @Version
 **/
public class HashLock implements com.xxp.lock.Lock {

    private final ConcurrentHashMap<String, Lock> lockMap = new ConcurrentHashMap<>();

    @Override
    public void lock(String key) {
        getLock(key).lock();
    }

    @Override
    public boolean tryLock(String key) {
        return getLock(key).tryLock();
    }

    @Override
    public boolean tryLock(String key, long time, TimeUnit unit) throws InterruptedException {
        return getLock(key).tryLock(time, unit);
    }

    /**
     * 如果对象key的hash值发生变化，可能导致把其它对象解锁，自己当前对象死锁
     *
     * @param key
     */
    @Override
    public void unLock(String key) {
        ReentrantLock lock = (ReentrantLock) getLock(key);
        int holdCount = lock.getQueueLength();
        lock.unlock();
        // 可能存在内存泄漏，如果获取队列数大于1，此时队列节点恰好过期，则remove方法不会被执行
        if (holdCount < 1) {
            removeLock(key);
        }
    }

    private Lock getLock(String key) {
        return lockMap.computeIfAbsent(key, o -> new ReentrantLock());
    }

    private Lock removeLock(String key) {
        return lockMap.remove(key);
    }
}
