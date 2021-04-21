package com.xxp.transport;

import com.xxp.common.URL;
import io.netty.channel.Channel;
import io.netty.util.HashedWheelTimer;
import io.netty.util.Timeout;
import io.netty.util.TimerTask;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author: xiexipeng@u51.com
 * @create: 2021/04/20 14:55:28
 * @description: future管理器
 * @Version V1.0
 **/
@Slf4j
public class ResponseFutureManager {

    private static final AtomicLong nextId = new AtomicLong(0);
    private static final Map<Long, Channel> channelMap = new ConcurrentHashMap<>();
    private static final Map<Long, ResponseFuture> futureMap = new ConcurrentHashMap<>();

    private static final ThreadFactory threadFactory = new ThreadFactory() {

        private final AtomicInteger mThreadNum = new AtomicInteger(0);

        @Override
        public Thread newThread(Runnable r) {
            String name = "netty-future-timeout-thread-%" + mThreadNum.getAndIncrement();
            Thread ret = new Thread(name);
            ret.setDaemon(true);
            return ret;
        }
    };

    private static final HashedWheelTimer outTimer = new HashedWheelTimer(threadFactory, 30, TimeUnit.MICROSECONDS);

    public static ResponseFuture newFuture(Channel channel, int timeout) {
        Long id = nextId.getAndIncrement();
        ResponseFuture future = new ResponseFuture(id, channel);
        channelMap.put(id, channel);
        futureMap.put(id, future);
        outTimer.newTimeout(new TimeoutTask(id), timeout, TimeUnit.MILLISECONDS);
        return future;
    }

    public static ResponseFuture getFutureById(Long id) {
        return futureMap.get(id);
    }

    public static Channel getChannelById(Long id) {
        return channelMap.get(id);
    }

    public static ResponseFuture removeFutureById(Long id) {
        return futureMap.remove(id);
    }

    public static Channel removeChannelById(Long id) {
        return channelMap.remove(id);
    }

    public static class TimeoutTask implements TimerTask {

        private Long requestId;

        public TimeoutTask(Long requestId) {
            this.requestId = requestId;
        }

        @Override
        public void run(Timeout timeout) {
            ResponseFuture future = futureMap.get(requestId);
            if (future == null) {
                log.warn("根据requestId:{}获取future为空", requestId);
                return;
            }
            future.completeExceptionally(new RuntimeException("请求超时"));
        }
    }

}
