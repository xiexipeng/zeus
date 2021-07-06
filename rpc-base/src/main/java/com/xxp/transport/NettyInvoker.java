package com.xxp.transport;

import com.xxp.common.URL;
import com.xxp.server.Server;
import io.netty.channel.ChannelFuture;

/**
 * @author: xiexipeng
 * @create: 2021/04/20 15:05:10
 * @description:
 * @Version
 **/
public class NettyInvoker {

    private NettyClient nettyClient;

    public NettyInvoker(NettyClient nettyClient) {
        this.nettyClient = nettyClient;
    }

    public ResponseFuture invoke(Server server, URL url){
        ChannelFuture future = nettyClient.getChannel().write(url.getParam());
        return null;
    }
}
