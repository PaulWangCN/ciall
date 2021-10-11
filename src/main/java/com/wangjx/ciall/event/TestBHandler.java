package com.wangjx.ciall.event;

import org.springframework.beans.BeanUtils;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Set;

/**
 * @ClassName: TestBHandler
 * @Description: 测试B事件处理器
 * @Author: wangjiaxing
 * @Date: 2021/06/15 11:12
 * @Version 1.0
 */
@EventHandler
public class TestBHandler extends BaseEventHandler {

    protected TestBHandler() {
        super("测试B事件处理器");
    }

    @PostConstruct
    private void init() {
        eventTypeEnums.add(EventTypeEnum.TEST_B);
    }

    @Override
    public Set<EventTypeEnum> listEventType() {
        return eventTypeEnums;
    }

    @Override
    public String handle(String msg) {
        System.out.println(name + "收到事件信息:" + msg);
        return "事件A处理完成";
    }
}
