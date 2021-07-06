package com.xxp.transport;

import io.netty.channel.Channel;

/**
 * @author: xiexipeng
 * @create: 2021/04/20 10:42:02
 * @description: netty服务
 * @Version V1.0
 **/
public class NettyServer {

    private Channel channel;

    public void bind(){
        // 1.创建bootstrap
        // 2.设置handle
        // 3.返回channel
    }

    public boolean isConnected(){
        if (channel != null && channel.isActive()){
            return true;
        }
        return false;
    }

    public Channel getChannel(){
        return channel;
    }
}
