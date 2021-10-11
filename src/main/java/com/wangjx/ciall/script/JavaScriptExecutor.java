package com.wangjx.ciall.script;

import org.springframework.stereotype.Component;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * @ClassName: JavaScriptExecutor
 * @Description: JS脚本执行器
 * @Author: wangjiaxing
 * @Date: 2021/10/11 16:56
 * @Version 1.0
 */
@Component
public class JavaScriptExecutor {

    public void exec(String js) {
        try {
            ScriptEngineManager manager = new ScriptEngineManager();
            ScriptEngine engine = manager.getEngineByName("js");
            System.out.println("准备执行JS脚本:" + js);
            String result = (String) engine.eval(js);
            System.out.println("JS脚本执行结果:" + result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
