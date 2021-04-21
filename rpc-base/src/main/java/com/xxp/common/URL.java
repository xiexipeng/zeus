package com.xxp.common;

import lombok.Data;

import java.util.Map;

/**
 * @author: xiexipeng@u51.com
 * @create: 2021/04/16 17:18:01
 * @description: 请求信息
 * @Version V1.0
 **/
@Data
public class URL {

    private String ip;

    private String port;

    private String serverName;

    private String methodName;

    private Long timeout;

    private Class<?>[] argumentTypes;

    private Object[] arguments;

    private Map<Object, Object> param;

    public String getAddress() {
        return ip + ":" + port;
    }
}
