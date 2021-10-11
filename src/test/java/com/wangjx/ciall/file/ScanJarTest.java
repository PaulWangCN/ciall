package com.wangjx.ciall.file;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Set;

/**
 * @ClassName: ScanJarTest
 * @Description: 扫描jar包测试类
 * @Author: wangjiaxing
 * @Date: 2021/10/11 16:36
 * @Version 1.0
 */
public class ScanJarTest {

    @Test
    public void test() throws IOException, ClassNotFoundException {
        ScanJar handlerScanner = new ScanJar("com.wangjx.ciall", true, null, null);
        Set<Class<?>> classes = handlerScanner.doScanAllClasses();
        for (Class clazz : classes) {
            System.out.println(clazz.getName());
        }
    }

}
