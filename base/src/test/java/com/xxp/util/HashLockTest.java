package com.xxp.util;

import com.xxp.lock.HashLock;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: xiexipeng@u51.com
 * @create: 2021/04/27 16:59:36
 * @description:
 * @Version
 **/
@Slf4j
public class HashLockTest {

    @Test
    public void test_lock() throws InterruptedException {
        ReentrantLock lock = new ReentrantLock();
        ExecutorService executorService = Executors.newFixedThreadPool(20);
        for (int i = 0; i < 10; i++) {
            executorService.execute(() -> {
                boolean isLock = false;
                try {
                    isLock = lock.tryLock(3000, TimeUnit.MILLISECONDS);
                    log.info(isLock + "");
                    if (!isLock) {
                        return;
                    }
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    if (isLock) {
                        lock.unlock();
                    }
                }
            });
        }
        Thread.sleep(30000);
    }

    @Test
    public void test_tryLock() throws InterruptedException {
        HashLock hashLock = new HashLock();
        AtomicInteger index = new AtomicInteger(0);
        ExecutorService executorService = Executors.newFixedThreadPool(20);
        for (int i = 0; i < 10; i++) {
            executorService.execute(() -> {
                boolean lock = false;
                try {
                    lock = hashLock.tryLock("test", 3000, TimeUnit.MILLISECONDS);
                    log.info(lock + ":" + index.incrementAndGet());
                    if (!lock) {
                        return;
                    }
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    if (lock) {
                        hashLock.unLock("test");
                    }
                }
            });
        }
        synchronized (hashLock) {
            hashLock.wait();
        }
    }

    @Test
    public void test_tryLock_time_out() throws InterruptedException {
        HashLock hashLock = new HashLock();
        AtomicInteger index = new AtomicInteger(0);
        ExecutorService executorService = Executors.newFixedThreadPool(20);
        for (int i = 0; i < 10; i++) {
            executorService.execute(() -> {
                boolean lock = false;
                try {
                    lock = hashLock.tryLock("test", 100, TimeUnit.MILLISECONDS);
                    log.info(lock + ":" + index.incrementAndGet());
                    if (!lock) {
                        return;
                    }
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    if (lock) {
                        hashLock.unLock("test");
                    }
                }
            });
        }
        synchronized (hashLock) {
            hashLock.wait();
        }
    }

}
