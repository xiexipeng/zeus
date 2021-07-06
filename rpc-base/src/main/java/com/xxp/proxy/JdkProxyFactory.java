package com.xxp.proxy;

import com.xxp.common.URL;
import com.xxp.service.Invoker;

import java.lang.reflect.Proxy;

/**
 * @author: xiexipeng
 * @create: 2021/05/05 16:12:17
 * @description:
 * @Version
 **/
public class JdkProxyFactory implements ProxyFactory {

    @Override
    public <T> T getProxy(Class<T> tClass, URL url, Invoker invoker) {
        return (T) Proxy.newProxyInstance(tClass.getClassLoader(), new Class<?>[]{tClass}, new InvokerHandler(invoker, url));
    }

}
