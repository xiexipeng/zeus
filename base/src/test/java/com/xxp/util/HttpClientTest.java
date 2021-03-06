package com.xxp.util;

import com.alibaba.fastjson.JSON;
import com.xxp.http.DefaultHttpClient;
import com.xxp.http.Request;
import com.xxp.http.Response;
import org.junit.Test;

import java.io.FileNotFoundException;

/**
 * @author: xiexipeng
 * @create: 2020/09/28 20:26:09
 * @description: httpclinet测试用例
 * @Version V1.0
 **/
public class HttpClientTest {

    @Test
    public void test404() {
        Response response = DefaultHttpClient.INSTANCE.get("http://localhost:8082/iface-server/api/v1/test/get/test1");
        System.out.println(response);
        assert response.getCode() == 404;
    }

    @Test
    public void test_no_param_get() {
        Response response = DefaultHttpClient.INSTANCE.get("http://localhost:8082/iface-server/api/v1/test/get");
        System.out.println(response);
        assert response.getCode() == 200;
    }

    @Test
    public void test_url_param_get() {
        Response response = DefaultHttpClient.INSTANCE.get("http://localhost:8082/iface-server/api/v1/test/get/param/111");
        System.out.println(response);
        assert response.getCode() == 200;
    }

    @Test
    public void test_url_param_get_testParam2() {
        Response response = DefaultHttpClient.INSTANCE.get("http://localhost:8082/iface-server/api/v1/test/get/param", MapUtil.createBuilder().put("name", "123").build());
        System.out.println(response);
        assert response.getCode() == 200;
    }

    @Test
    public void test_url_param_get_testParam2_2() {
        Response response = DefaultHttpClient.INSTANCE.get("http://localhost:8082/iface-server/api/v1/test/get/param2", MapUtil.createBuilder().put("name", "123").put("other", "333").build());
        System.out.println(response);
        assert response.getCode() == 200;
    }

    @Test
    public void test_url_param_get_testParam3() {
        Response response = DefaultHttpClient.INSTANCE.get("http://localhost:8082/iface-server/api/v1/test/get/param/body", "aa");
        System.out.println(response);
        assert response.getCode() == 200;
    }

    @Test
    public void test_url_param_get_testParam3_2() {
        Response response = DefaultHttpClient.INSTANCE.get("http://localhost:8082/iface-server/api/v1/test/get/param/body2", MapUtil.createBuilder().put("name", "123").build(), "aa");
        System.out.println(response);
        assert response.getCode() == 200;
    }

    @Test
    public void test_url_param_get_testParam3_2_1() {
        Response response = DefaultHttpClient.INSTANCE.get(
                MapUtil.createBuilder()
                        .put("url", "http://localhost:8082/iface-server/api/v1/test/get/param/body2")
                        .put("urlParam", MapUtil.createBuilder("name", "123").build())
                        .put("bodyJson", "aa").build());
        System.out.println(response);
        assert response.getCode() == 200;
    }

    @Test
    public void test_url_param_post_testParam() {
        Response response = DefaultHttpClient.INSTANCE.post("http://localhost:8082/iface-server/api/v1/test/post/param", MapUtil.createBuilder().put("name", "123").build(), "aa");
        System.out.println(response);
        assert response.getCode() == 200;
    }

    @Test
    public void test_url_param_post_testParam2() {
        Response response = DefaultHttpClient.INSTANCE.post(
                MapUtil.createBuilder()
                        .put("url", "http://localhost:8082/iface-server/api/v1/test/post/body")
                        .put("params", MapUtil.createBuilder("name", "123").build())
                        .put("headers", MapUtil.createBuilder("Content-Type", "application/json").build()).build());
        System.out.println(response);
        assert response.getCode() == 200;
    }

    @Test
    public void test_no_param_get_timeout() {
        Response response = DefaultHttpClient.INSTANCE.get(Request.builder().url("http://localhost:8082/iface-server/api/v1/test/get").timeoutMs(1000).build());
        System.out.println(response);
        assert response.getCode() == 200;
    }

    @Test
    public void test_no_param_get_double() {
        Response response = DefaultHttpClient.INSTANCE.get(Request.builder().url("http://localhost:8082/iface-server/api/v1/test/get").build());
        System.out.println(response);
        assert response.getCode() == 200;
    }

    @Test
    public void test_post_file() throws FileNotFoundException {
//        File file = TestUtil.getFileFromClasspath("excel/名单导入模版.xlsx");
//        Response response = DefaultHttpClient.INSTANCE.post(Request.builder().url("http://localhost:8082/iface-server/api/v1/test/post/file").file(file).build());
//        System.out.println(response);
//        assert response.getCode() == 200;
    }

    @Test
    public void test_get_mock() {
        Response response = DefaultHttpClient.INSTANCE.get("http://10.247.23.144:5152/user-data-center/api/v1/label/query/queryWithLabelIdList",
                JSON.toJSONString(MapUtil.createBuilder().put("userId", "1801100666").put("labelIds", 1).put("userGroup", 1).build()));
        System.out.println(response);
    }

    public static void main(String[] args) {
        String url = "https://api.lianlianlvyou.com/v1/wx/product2?Authorization=olgDYsrgB9y0S31_vwa9L_1yg9JQ&timestamp=1619854965719&id=447946&locationid=13&i=wxed8251e55bcaa954&longitude=&latitude=";
        String base = "https://api.lianlianlvyou.com/wx/city/list?timestamp=1607060901447&ll_client=2&ll_versionCode=1&ll_version=1&Authorization=oo25swO-KY0lqBxiCxp2h75MdE8E&i=wx3623dfa9e7270632&locationid=0";
        Response response = DefaultHttpClient.INSTANCE.get(url);
        System.out.println(response.getBodyJSON());
    }

}
