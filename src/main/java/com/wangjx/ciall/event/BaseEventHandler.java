package com.wangjx.ciall.event;


import java.util.HashSet;
import java.util.Set;

/**
 * @ClassName: BaseEventHandler
 * @Description: 事件处理器抽象父类
 * @Author: wangjiaxing
 * @Date: 2021/06/09 16:37
 * @Version 1.0
 */
public abstract class BaseEventHandler implements IEventHandler {

    /**
     * 处理器名称
     */
    protected String name;

    /**
     * 处理器处理的事件类型枚举
     */
    protected Set<EventTypeEnum> eventTypeEnums = new HashSet<>();

    /**
     * 默认构造器
     * @param name 处理器名称
     */
    protected BaseEventHandler(String name) {
        this.name = name;
    }

    /**
     * 获取该处理器支持的所有事件类型
     * @return
     */
    public Set<EventTypeEnum> listEventType() {
        return eventTypeEnums;
    }

    /**
     * 获取处理器名称
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * 处理器实际处理方法，由子类自己实现
     * @param msg 事件信息
     * @return
     */
    public abstract String handle(String msg);

}
