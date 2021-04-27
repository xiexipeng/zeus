package com.xxp.http;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.File;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Map;

/**
 * @author: xiexipeng@u51.com
 * @create: 2020/09/17 13:51:09
 * @description: HTTP请求
 * @Version V1.0
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Request {

    /**
     * 请求方法 GET、POST等
     */
    private String method = "GET";

    /**
     * 请求URL
     */
    private String url;

    /**
     * GET请求的url参数
     */
    private Map<String, String> urlParam;

    /**
     * map类型的请求体
     */
    private Map<String, Object> params;

    /**
     * json字符串请求体
     */
    private String bodyJson;

    /**
     * 请求头
     */
    private Map<String, String> headers;

    /**
     * 请求超时时间，单位：毫秒
     */
    private int timeoutMs;

    /**
     * 是否重试
     */
    private boolean isRetry;

    /**
     * 最大重试次数
     */
    private int maxRetry;

    /**
     * 是否支持熔断机制
     */
    private boolean isSupportCircuitBreak;

    /**
     * 流处理
     */
    private InputStream inputStream;

    /**
     * 文件
     */
    private File file;

    /**
     * 是否缓存结果
     */
    private boolean isCache;

    /**
     * 返回状态码非200时所抛的异常，可由调用方指定
     * 可以指定策略，按超时、404等不同返回异常做不同处理
     */
    private Exception exception;

    @Override
    public String toString() {
        Arrays.asList("存续", "在业", "开业", "正常", "登记", "在营", "登记成立", "仍注册",
                "核准设立", "迁入", "迁出");
        return "Request{" +
                "url='" + url + '\'' +
                ", params=" + params +
                ", headers=" + headers +
                ", timeoutMs=" + timeoutMs +
                '}';
    }
}
