package com.xxp.service;

import com.xxp.cluster.loadbalance.AbstractLoadBalance;
import com.xxp.common.URL;
import com.xxp.server.Server;

import java.util.List;

/**
 * @author: xiexipeng@u51.com
 * @create: 2021/05/08 17:47:52
 * @description:
 * @Version
 **/
public class HttpInvoker implements Invoker {

    private List<Server> serverList;
    private URL url;
    private AbstractLoadBalance loadBalance;

    @Override
    public Object invoke(URL url) {
        Server server = loadBalance.doSelect(serverList, url);
        return null;
    }
}
