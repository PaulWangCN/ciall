package com.wangjx.ciall.encrypt;

import org.junit.jupiter.api.Test;

/**
 * @ClassName: AESUtilsTest
 * @Description: AES加解密工具类测试
 * @Author: wangjiaxing
 * @Date: 2021/10/11 17:24
 * @Version 1.0
 */
public class AESUtilsTest {

    @Test
    public void test() throws Exception {
        String strText = "123";
        String key = "test-key-1234567";
        String encrypt = AESUtils.encrypt(strText, key);
        System.out.println("原文:" + strText + ",密码:" + key + "经过加密得到密文:" + encrypt);
        String decrypt = AESUtils.decrypt(encrypt, key);
        System.out.println("密文:" + encrypt + ",密码:" + key + "经过解密得到原文:" + decrypt);
    }

}
