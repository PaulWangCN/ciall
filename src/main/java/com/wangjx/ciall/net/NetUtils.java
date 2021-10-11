package com.wangjx.ciall.net;

import java.io.IOException;
import java.net.*;
import java.util.Enumeration;

/**
 * @program: ciall
 * @description: 网络工具类
 * @author: wangjiaxing
 * @created: 2021/02/24 15:32
 */
public final class NetUtils {

    /**
     * 测试到指定IP设备的端口是否可达
     * @param ip 目标设备IP
     * @param port 目标设备端口
     * @param timeout  超时时间 单位ms
     */
    public static boolean checkIPAndPortReachable(String ip, int port, int timeout) {
        InetAddress inetAddress = null;
        try {
            inetAddress = InetAddress.getByName(ip);
        } catch (UnknownHostException e) {
            return false;
        }
        return checkIPAndPortReachable(inetAddress, port, timeout);
    }

    /**
     * 测试到指定IP设备的端口是否可达
     * @param remoteAddr  目标设备
     * @param port  设备端口
     * @param timeout  超时时间 单位ms
     */
    public static boolean checkIPAndPortReachable(InetAddress remoteAddr, int port, int timeout) {
        String retIP = null;
        Enumeration<NetworkInterface> netInterfaces;
        try {
            netInterfaces = NetworkInterface.getNetworkInterfaces();
            while (netInterfaces.hasMoreElements()) {
                NetworkInterface ni = netInterfaces.nextElement();
                Enumeration<InetAddress> localAddrs = ni.getInetAddresses();
                while (localAddrs.hasMoreElements()) {
                    InetAddress localAddr = localAddrs.nextElement();
                    if (isReachable(localAddr, remoteAddr, port, timeout)) {
                        retIP = localAddr.getHostAddress();
                        break;
                    }
                }
            }
        } catch (SocketException e) {
            System.err.println("Error occurred while listing all the local network addresses.");
        }
        return retIP != null;
    }

    private static boolean isReachable(InetAddress localInetAddr, InetAddress remoteInetAddr, int port, int timeout) {
        boolean isReachable = false;
        Socket socket = null;
        try {
            socket = new Socket();
            // 端口号设置为 0 表示在本地挑选一个可用端口进行连接
            SocketAddress localSocketAddr = new InetSocketAddress(localInetAddr, 0);
            socket.bind(localSocketAddr);
            InetSocketAddress endpointSocketAddr = new InetSocketAddress(remoteInetAddr, port);
            socket.connect(endpointSocketAddr, timeout);
            isReachable = true;
        } catch (IOException e) {
            System.err.println("NetUtils.isReachable() exception");
        } finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    System.err.println("Error occurred while closing socket..");
                }
            }
        }
        return isReachable;
    }

    /**
     * 简单检测IP地址是否可达，底层是ping实现
     * @param ip 目标IP
     */
    public static boolean checkIPReachable(String ip, int timeout) {
        try {
            InetAddress address = InetAddress.getByName(ip);//ping this IP
            if (!(address instanceof Inet4Address) && !(address instanceof Inet6Address)) {
                return false;
            }
            if (address.isReachable(timeout)) {
                return true;
            }
            Enumeration<NetworkInterface> netInterfaces = NetworkInterface.getNetworkInterfaces();
            while (netInterfaces.hasMoreElements()) {
                NetworkInterface ni = netInterfaces.nextElement();
                if (address.isReachable(ni, 0, 5000)) {
                    return true;
                }
            }
        } catch (Exception e) {
            System.err.println("NetUtils.checkIPReachable() exception");
        }
        return false;
    }

    public static String getLocalIP() {
        String ip = "-1";
        try {
            ip = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            System.err.println("获取不到本机IP");
        }
        return ip;
    }
}
