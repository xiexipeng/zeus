package com.xxp.http;

import lombok.Data;

import java.util.Map;

/**
 * @author: xiexipeng@u51.com
 * @create: 2020/09/21 22:16:33
 * @description: HTTP请求返回结果
 * @Version V1.0
 **/
@Data
public class Response<T> {

    /**
     * 响应码
     */
    private int code;

    /**
     * 响应头
     */
    private Map<String, String> headers;

    /**
     * 返回结果
     */
    private String bodyJSON;

    /**
     * 返回数据项
     */
    private T data;

    @Override
    public String toString() {
        return "Response{" +
                "code=" + code +
                ", headers=" + headers +
                ", bodyJSON='" + bodyJSON + '\'' +
                '}';
    }
}
