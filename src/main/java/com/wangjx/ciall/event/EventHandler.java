package com.wangjx.ciall.event;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * @ClassName: EventHandler
 * @Description: 事件处理类声明注解
 * @Author: wangjiaxing
 * @Date: 2021/06/09 16:31
 * @Version 1.0
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface EventHandler {

    String value() default "";

}
