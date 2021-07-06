package com.xxp.cluster.register;

import com.xxp.common.URL;
import com.xxp.server.Server;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: xiexipeng
 * @create: 2021/05/07 19:30:04
 * @description:
 * @Version
 **/
public class ZookeeperRegistry implements Registry {

    private String serviceName;
    private String serviceTag;
    private List<URL> urls;

    @Override
    public void registry(String serviceName, String serviceTag) {

    }

    @Override
    public List<Server> subscribe(String serviceName, String serviceTag) {
        // TODO save urls
        return new ArrayList<>();
    }
}
