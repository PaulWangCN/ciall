package com.wangjx.ciall.script;

import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyObject;
import org.springframework.stereotype.Component;

import java.io.File;

/**
 * @ClassName: GroovyScriptExecutor
 * @Description: groovy脚本执行器
 * @Author: wangjiaxing
 * @Date: 2021/10/11 16:43
 * @Version 1.0
 */
@Component
public class GroovyScriptExecutor {

    public void exec(String scriptFileName, String methodName, Object params) {
        try {
            File scriptFile = new File(scriptFileName);
            GroovyClassLoader loader = new GroovyClassLoader();
            Class fileCreator = loader.parseClass(scriptFile);
            GroovyObject object = (GroovyObject) fileCreator.newInstance();
            Object o = object.invokeMethod(methodName, params);
            if (o != null) {
                System.out.println("脚本执行结果:" + o);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
