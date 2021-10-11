package com.wangjx.ciall.script


/**
 * @ClassName: GroovyTestScript
 * @Description: groovy测试脚本
 * @Author: wangjiaxing
 * @Date: 2021/04/08 22:23
 * @Version 1.0
 */
class GroovyTestScript {

    String call(String params) {
        println("groovy测试脚本开始执行")
        println("收到参数:" + params)
        return "groovy脚本运行完毕"
    }

}
