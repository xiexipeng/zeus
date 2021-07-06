package com.xxp.lock;

import java.util.concurrent.TimeUnit;

/**
 * @author: xiexipeng
 * @create: 2021/04/27 16:48:45
 * @description:
 * @Version
 **/
public interface Lock {

    void lock(String key);

    boolean tryLock(String key);

    boolean tryLock(String key, long time, TimeUnit unit) throws Exception;

    /**
     * 如果对象key的hash值发生变化，可能导致把其它对象解锁，自己当前对象死锁
     *
     * @param key
     */
    void unLock(String key);
}
