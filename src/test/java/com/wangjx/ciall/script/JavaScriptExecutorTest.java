package com.wangjx.ciall.script;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;

/**
 * @ClassName: JavaScriptExecutorTest
 * @Description: JS脚本执行器测试
 * @Author: wangjiaxing
 * @Date: 2021/10/11 17:01
 * @Version 1.0
 */
@SpringBootTest
public class JavaScriptExecutorTest {

    @Autowired
    private JavaScriptExecutor javaScriptExecutor;

    @Test
    public void test() throws IOException {
        String js = FileUtils.readFileToString(new File("testfile/test.js"), "utf-8");
        javaScriptExecutor.exec(js);
    }

}
