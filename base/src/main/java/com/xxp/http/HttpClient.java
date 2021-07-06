package com.xxp.http;

import java.util.Map;

/**
 * @author: xiexipeng
 * @create: 2020/09/14 17:40:57
 * @description: HTTP调用client
 * @Version
 **/
public interface HttpClient {

    Response get(String url);

    Response get(String url, Map<String, String> urlParam);

    Response get(String url, String bodyJson);

    Response get(String url, Map<String, String> urlParam, String bodyJson);

    Response get(Map<String, Object> requestMap);

    Response get(Request request);

    Response post(String url);

    Response post(String url, Map<String, String> urlParam);

    Response post(String url, String bodyJson);

    Response post(String url, Map<String, String> urlParam, String bodyJson);

    Response post(Map<String, Object> requestMap);

    Response post(Request request);
}
