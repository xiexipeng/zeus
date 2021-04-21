package com.xxp.transport;

import io.netty.channel.Channel;

import java.util.concurrent.CompletableFuture;

/**
 * @author: xiexipeng@u51.com
 * @create: 2021/04/20 14:49:27
 * @description:
 * @Version
 **/
public class ResponseFuture extends CompletableFuture<NettyResponse> {

    private Long id;
    private Channel nettyChannel;

    public ResponseFuture(Long id, Channel nettyChannel) {
        this.id = id;
        this.nettyChannel = nettyChannel;
    }
}
