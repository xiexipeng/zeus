package com.xxp.transport;

import com.xxp.common.URL;
import com.xxp.util.NamedThreadFactory;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.nio.NioEventLoopGroup;
import lombok.extern.slf4j.Slf4j;

/**
 * @author: xiexipeng
 * @create: 2021/04/20 10:42:19
 * @description: netty客户端
 * @Version V1.0
 **/
@Slf4j
public class NettyClient {

    private Channel channel;

    public NettyClient(URL url) {
        init(url);
    }

    public void init(URL url){
        // 1.创建bootstrap
        // 2.设置handle
    }

    public Channel connect(){
        // 3.返回channel
        return null;
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
