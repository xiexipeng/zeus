package com.xxp.http;

import cn.hutool.core.util.RandomUtil;
import com.alibaba.fastjson.JSONObject;
import com.xxp.util.MapUtil;
import lombok.extern.slf4j.Slf4j;
import org.asynchttpclient.*;
import org.asynchttpclient.request.body.multipart.FilePart;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * @author: xiexipeng@u51.com
 * @create: 2020/09/21 22:22:36
 * @description: HTTP调用client默认实现
 * @Version V1.0
 **/
@Slf4j
public class DefaultHttpClient implements HttpClient {

//    private static final String BOUNDARY = "--------------------XXP_" + RandomUtil.randomString(16);

    private AsyncHttpClient httpClient = new DefaultAsyncHttpClient();

    public static final DefaultHttpClient INSTANCE = new DefaultHttpClient();

    private DefaultHttpClient() {

    }

    @Override
    public Response get(String url) {
        return get(Request.builder().method("GET").url(url).build());
    }

    @Override
    public Response get(String url, Map<String, String> urlParam) {
        return get(Request.builder().method("GET").url(url).urlParam(urlParam).build());
    }

    @Override
    public Response get(String url, String bodyJson) {
        return get(Request.builder().method("GET").url(url).bodyJson(bodyJson).build());
    }

    @Override
    public Response get(String url, Map<String, String> urlParam, String bodyJson) {
        return get(Request.builder().method("GET").url(url).urlParam(urlParam).bodyJson(bodyJson).build());
    }

    @Override
    public Response get(Map<String, Object> requestMap) {
        return get(MapUtil.mapToObject(requestMap, Request.class));
    }

    @Override
    public Response get(Request request) {
        request.setMethod("GET");
        return doCall(request);
    }

    @Override
    public Response post(String url) {
        return post(Request.builder().method("POST").url(url).build());
    }

    @Override
    public Response post(String url, Map<String, String> urlParam) {
        return post(Request.builder().method("POST").url(url).urlParam(urlParam).build());
    }

    @Override
    public Response post(String url, String bodyJson) {
        return post(Request.builder().method("POST").url(url).bodyJson(bodyJson).build());
    }

    @Override
    public Response post(String url, Map<String, String> urlParam, String bodyJson) {
        return post(Request.builder().method("POST").url(url).urlParam(urlParam).bodyJson(bodyJson).build());
    }

    @Override
    public Response post(Map<String, Object> requestMap) {
        Request request = MapUtil.mapToObject(requestMap, Request.class);
        request.setMethod("POST");
        return post(request);
    }

    @Override
    public Response post(Request request) {
        request.setMethod("POST");
        return doCall(request);
    }

    private Response doCall(Request request) {
        ListenableFuture<org.asynchttpclient.Response> responseListenableFuture = httpClient.executeRequest(transferNettyRequest(request));
        try {
            org.asynchttpclient.Response response = responseListenableFuture.get();
            Response result = new Response();
            result.setCode(response.getStatusCode());
            result.setBodyJSON(response.getResponseBody());
            return result;
        } catch (InterruptedException e) {
            log.info("调用异常", e);
            throw new RuntimeException("调用异常");
        } catch (ExecutionException e) {
            log.info("调用异常", e);
            throw new RuntimeException("调用异常");
        }
    }

    /**
     * 客户端request请求转换为netty的request请求
     *
     * @param request
     * @return
     */
    private org.asynchttpclient.Request transferNettyRequest(Request request) {
        RequestBuilder builder = new RequestBuilder();
        builder.setHeader("Content-Type", "application/json");
        builder.setMethod(request.getMethod());
        String url = request.getUrl();
        // 拼接url参数
        if (!CollectionUtils.isEmpty(request.getUrlParam())) {
            Map<String, String> urlParam = request.getUrlParam();
            StringBuffer sb = new StringBuffer();
            urlParam.entrySet().forEach(it -> {
                sb.append(it.getKey()).append("=").append(it.getValue()).append("&");
            });
            if (sb.length() > 0) {
                url = url + "?" + sb.deleteCharAt(sb.length() - 1).toString();
            }
        }
        builder.setUrl(url);
        // 设置请求头信息
        if (!CollectionUtils.isEmpty(request.getHeaders())) {
            request.getHeaders().entrySet().forEach(it -> {
                builder.setHeader(it.getKey(), it.getValue());
            });
        }
        // 上传文件输入流
        if (request.getFile() != null) {
//            builder.setHeader("Content-Type", "multipart/form-data; boundary=" + BOUNDARY);
            builder.setBodyParts(Arrays.asList(new FilePart("filename", request.getFile())));
            return builder.build();
        }
        // 设置消息体
        if (!CollectionUtils.isEmpty(request.getParams())) {
            builder.setBody(JSONObject.toJSONString(request.getParams()));
        }
        if (!StringUtils.isEmpty(request.getBodyJson())) {
            builder.setBody(request.getBodyJson());
        }
        // 设置请求超时时间
        if (request.getTimeoutMs() != 0) {
            builder.setRequestTimeout(request.getTimeoutMs());
        }
        return builder.build();
    }

}
