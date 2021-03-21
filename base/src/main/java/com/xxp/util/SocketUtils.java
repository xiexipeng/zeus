package com.xxp.util;

import lombok.experimental.UtilityClass;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

/**
 * @author: xiexipeng
 * @create: 2021/03/18 16:30:22
 * @description: 网络工具类
 * @Version V1.0
 **/
@UtilityClass
public class SocketUtils {

    public String getLocalIp() throws SocketException {
        // 获取本机的所有网络接口
        Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
        InetAddress ip = null;
        while (networkInterfaces.hasMoreElements()){
            NetworkInterface networkInterface = networkInterfaces.nextElement();
            // 获取与改网络接口绑定的IP地址，一般只有一个
            Enumeration<InetAddress> inetAddresses = networkInterface.getInetAddresses();
            while (inetAddresses.hasMoreElements()){
                ip = inetAddresses.nextElement();
//                boolean isIpv4 = false;
//                if (ip instanceof Inet4Address){
//                    isIpv4 = true;
//                }
//                System.out.print(ip.isSiteLocalAddress() + "==" + ip.isLoopbackAddress() + "==" + ip.getHostAddress() + "==" + isIpv4);
//                System.out.println();
                // 取内网IP地址，非127.0.0.1地址，且是IPv4地址
                if (ip.isSiteLocalAddress() && !ip.isLoopbackAddress() && ip instanceof Inet4Address){
                    return ip.getHostAddress();
                }
            }
        }
        return null;
    }
}
