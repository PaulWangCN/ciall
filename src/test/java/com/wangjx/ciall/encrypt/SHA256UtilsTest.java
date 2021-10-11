package com.wangjx.ciall.encrypt;

import org.junit.jupiter.api.Test;

/**
 * @ClassName: SHA256UtilsTest
 * @Description: SHA256工具类测试
 * @Author: wangjiaxing
 * @Date: 2021/10/11 17:16
 * @Version 1.0
 */
public class SHA256UtilsTest {

    @Test
    public void test() {
        String strText = "123";
        String encrypt = SHA256Utils.encrypt(strText);
        System.out.println("SHA256加密" + strText + "结果:" + encrypt);
    }

}
