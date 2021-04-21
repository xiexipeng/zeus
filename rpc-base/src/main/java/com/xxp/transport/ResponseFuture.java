package com.xxp.transport;

import io.netty.channel.Channel;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author: xiexipeng@u51.com
 * @create: 2021/04/20 14:49:27
 * @description:
 * @Version
 **/
@Slf4j
public class ResponseFuture extends CompletableFuture<NettyResponse> {

    private Long id;
    private Channel nettyChannel;

    private AtomicBoolean isDone = new AtomicBoolean(false);

    public ResponseFuture(Long id, Channel nettyChannel) {
        this.id = id;
        this.nettyChannel = nettyChannel;
    }

    public void received(NettyResponse response, Throwable e) {
        try {
            ResponseFuture future = ResponseFutureManager.removeFutureById(response.getId());
            if (future == null){
                log.warn("");
                return;
            }
            future.doReceived(response, e);
        } finally {
            ResponseFutureManager.removeChannelById(response.getId());
        }
    }

    private void doReceived(NettyResponse response, Throwable e) {
        // 防止正常返回和超时线程的并发，只能处理一个结果
//        if (!isDone.compareAndSet(false, true)) {
//            return;
//        }
        if (e != null) {
            this.completeExceptionally(e);
            return;
        }
        this.complete(response);
    }

}
