package com.xxp.service;

import com.xxp.common.URL;

/**
 * @author: xiexipeng@u51.com
 * @create: 2021/05/04 15:58:36
 * @description: 服务提供者代理类
 * @Version V1.0
 **/
public interface Invoker {

    /**
     * 执行RPC请求
     */
    Object invoke(URL url);
}
