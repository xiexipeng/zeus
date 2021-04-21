package com.xxp.client.loadbalance;

import com.xxp.common.URL;
import com.xxp.server.Server;

import java.util.List;

/**
 * @author: xiexipeng@u51.com
 * @create: 2021/04/16 17:07:28
 * @description:
 * @Version
 **/
public abstract class AbstractLoadBalance {

    public Server select(List<Server> serverList, URL url){
        if (serverList == null || serverList.size() == 0){
            return null;
        }
        if (serverList.size() == 1){
            return serverList.get(0);
        }
        return doSelect(serverList,url);
    }

    public abstract Server doSelect(List<Server> serverList, URL url);
}
