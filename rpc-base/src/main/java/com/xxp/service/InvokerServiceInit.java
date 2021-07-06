package com.xxp.service;

import org.springframework.beans.factory.InitializingBean;

/**
 * @author: xiexipeng
 * @create: 2021/05/04 16:07:12
 * @description: 服务提供者初始化
 * @Version V1.0
 **/
public class InvokerServiceInit implements InitializingBean {

    /**
     * 扫描所有带有@BaseService的注解类，构造对应的Invoker
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        export();
    }


    private void export(){

    }
}
