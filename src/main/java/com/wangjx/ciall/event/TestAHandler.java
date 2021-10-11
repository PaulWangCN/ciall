package com.wangjx.ciall.event;


import javax.annotation.PostConstruct;
import java.util.Set;

/**
 * @ClassName: TestAHandler
 * @Description: 测试A事件处理器
 * @Author: wangjiaxing
 * @Date: 2021/06/09 15:25
 * @Version 1.0
 */
@EventHandler
public class TestAHandler extends BaseEventHandler {

    protected TestAHandler() {
        super("测试A事件处理器");
    }

    @PostConstruct
    private void init() {
        eventTypeEnums.add(EventTypeEnum.TEST_A);
    }

    @Override
    public String handle(String msg) {
        System.out.println(name + "收到事件信息:" + msg);
        return "事件A处理完成";
    }

    @Override
    public Set<EventTypeEnum> listEventType() {
        return eventTypeEnums;
    }
}
