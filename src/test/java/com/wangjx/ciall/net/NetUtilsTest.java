package com.wangjx.ciall.net;

import org.junit.jupiter.api.Test;

/**
 * @ClassName: NetUtilsTest
 * @Description: 网络工具类测试
 * @Author: wangjiaxing
 * @Date: 2021/10/11 16:34
 * @Version 1.0
 */
public class NetUtilsTest {

    @Test
    public void test() {
        String host = "11.11.11.11";
        int port = 9999;
        int timeout = 3000;
        boolean b = NetUtils.checkIPAndPortReachable(host, port, timeout);
        System.out.println(host + ":" + port + " 网络" + (b ? "可达" : "不可达"));
    }

}
