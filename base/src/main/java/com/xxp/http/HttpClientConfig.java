package com.xxp.http;

/**
 * @author: xiexipeng@u51.com
 * @create: 2021/05/21 16:42:06
 * @description:
 * @Version
 **/
public class HttpClientConfig {

    /**
     * netty io线程池线程工厂设置线程名字
     * new NioEventLoopGroup(config.getIoThreadsCount(), threadFactory);
     */
    private String threadPoolName;

    /**
     * netty io线程数
     */
    private int ioThreadsCount;

    /**
     * netty 连接超时时间
     * bootstrap.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, config.getConnectTimeout());
     */
    private int connectTimeout;

    /**
     * 数据读取超时时间
     */
    private int readTimeout;

    /**
     * 请求超时时间
     */
    private int requestTimeout;

    /**
     * 最大请求重试次数
     */
    private int maxRequestRetry;

    /**
     * channel连接最大存活时间，默认60秒
     */
    private int pooledConnectionIdleTimeout;

    /**
     * 最大连接数
     */
    private int maxConnections;

    /**
     * 单机最大连接数
     */
    private int maxConnectionsPerHost;
}
