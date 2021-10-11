package com.wangjx.ciall.event;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @ClassName: EventHandlerManagerTest
 * @Description: 事件处理器管理器测试
 * @Author: wangjiaxing
 * @Date: 2021/10/11 16:20
 * @Version 1.0
 */
@SpringBootTest
public class EventHandlerManagerTest {

    @Autowired
    private EventHandlerManager eventHandlerManager;

    @Test
    public void test() {
        String resultA = eventHandlerManager.process(EventTypeEnum.TEST_A, "发送一条数据到处理器A");
        System.out.println("事件A处理器结果:" + resultA);
        String resultB = eventHandlerManager.process(EventTypeEnum.TEST_B, "发送一条数据到处理器B");
        System.out.println("事件B处理器结果:" + resultB);
    }

}
