package com.xxp.proxy;

import java.lang.reflect.Proxy;

/**
 * @author: xiexipeng@u51.com
 * @create: 2021/05/05 16:12:17
 * @description:
 * @Version
 **/
public class JdkProxyFactory implements ProxyFactory {

    @Override
    public <T> T getProxy(Class<T> tClass) {
        return (T) Proxy.newProxyInstance(tClass.getClassLoader(), new Class<?>[]{tClass}, new InvokerHandler());
    }

}
