package com.xxp.lock;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: xiexipeng@u51.com
 * @create: 2021/04/14 11:44:15
 * @description: 分段key锁
 * @Version V1.0
 **/
public class SegmentLock implements com.xxp.lock.Lock {

    // 分段锁容量
    private int segment = 16;

    private ConcurrentHashMap<Integer, Lock> concurrentHashMap = new ConcurrentHashMap<>();

    public SegmentLock() {
    }

    public SegmentLock(int segment) {
        this.segment = segment;
    }

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
        getLock(key).unlock();
    }

    private Lock getLock(String key) {
        // 存在hash碰撞，可能导致不同key获取到相同的锁
        int index = key.hashCode() % segment;
        return concurrentHashMap.computeIfAbsent(index, o -> new ReentrantLock());
    }

}
