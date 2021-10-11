package com.wangjx.ciall.encrypt;

import org.junit.jupiter.api.Test;

/**
 * @ClassName: MD5UtilsTest
 * @Description: MD5工具类测试
 * @Author: wangjiaxing
 * @Date: 2021/10/11 17:20
 * @Version 1.0
 */
public class MD5UtilsTest {

    @Test
    public void test() {
        String strText = "123";
        String encrypt = MD5Utils.encrypt(strText);
        System.out.println("MD5加密" + strText + "结果:" + encrypt);
    }

}
