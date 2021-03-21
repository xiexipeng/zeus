package com.xxp.util;

import org.junit.jupiter.api.Test;

import java.net.SocketException;

/**
 * @author: xiexipeng
 * @create: 2021/03/18 16:44:09
 * @description: 网络工具类测试用例
 * @Version V1.0
 **/
public class SocketUtilsTest {

    @Test
    public void test_getLocalIp() throws SocketException {
        System.out.println(SocketUtils.getLocalIp());
    }
}
