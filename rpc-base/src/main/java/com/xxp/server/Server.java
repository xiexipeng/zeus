package com.xxp.server;

import lombok.Data;

/**
 * @author: xiexipeng@u51.com
 * @create: 2021/04/16 17:25:48
 * @description: 服务信息
 * @Version V1.0
 **/
@Data
public class Server {

    private String host;

    private String port;

    private String groupName;

    private String serverName;

    private int weight;
}
