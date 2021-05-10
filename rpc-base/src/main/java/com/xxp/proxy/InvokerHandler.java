package com.xxp.proxy;

import com.xxp.common.URL;
import com.xxp.service.Invoker;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author: xiexipeng@u51.com
 * @create: 2021/05/05 16:14:50
 * @description:
 * @Version
 **/
public class InvokerHandler implements InvocationHandler {

    private Invoker invoker;
    private URL url;

    public InvokerHandler(Invoker invoker, URL url) {
        this.invoker = invoker;
        this.url = url;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        url.setMethod(method);
        url.setArguments(args);
        return invoker.invoke(url);
    }
}
