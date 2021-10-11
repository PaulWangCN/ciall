package com.wangjx.ciall.event;


import java.util.Set;

/**
 * @ClassName: IEventHandler
 * @Description: 事件处理器接口
 * @Author: wangjiaxing
 * @Date: 2021/06/09 15:21
 * @Version 1.0
 */
public interface IEventHandler {
    /**
     *
     * @param msg 事件信息
     * @return
     */
    String handle(String msg);

    /**
     * 获取该处理器支持的所有事件类型
     * @return
     */
    Set<EventTypeEnum> listEventType();

    /**
     * 获取处理器名称
     * @return
     */
    String getName();

}
