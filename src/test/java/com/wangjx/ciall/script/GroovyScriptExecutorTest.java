package com.wangjx.ciall.script;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @ClassName: GroovyScriptExecutorTest
 * @Description: groovy脚本执行器测试
 * @Author: wangjiaxing
 * @Date: 2021/10/11 16:50
 * @Version 1.0
 */
@SpringBootTest
public class GroovyScriptExecutorTest {

    @Autowired
    private GroovyScriptExecutor groovyScriptExecutor;

    @Test
    public void test() {
        groovyScriptExecutor.exec("testfile/Test.groovy", "call", "随便一个测试参数");
    }

}
