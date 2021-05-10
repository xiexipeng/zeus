package com.xxp.proxy;

import com.xxp.common.URL;
import com.xxp.service.Invoker;

/**
 * @author: xiexipeng@u51.com
 * @create: 2021/05/04 15:57:32
 * @description: 远程服务代理类
 * @Version V1.0
 **/
public interface ProxyFactory {

    /**
     * 获取代理对象
     *
     * @param tClass
     * @param <T>
     * @return
     */
    <T> T getProxy(Class<T> tClass, URL url, Invoker invoker);
}
