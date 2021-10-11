package com.wangjx.ciall.event;

import java.util.Optional;

/**
 * @ClassName: EventTypeEnum
 * @Description: 事件类型枚举
 * @Author: wangjiaxing
 * @Date: 2021/06/09 15:18
 * @Version 1.0
 */
public enum EventTypeEnum {

    /**
     * 测试A事件
     */
    TEST_A(1),
    /**
     * 测试B事件
     */
    TEST_B(2),
    ;

    private int type;

    EventTypeEnum(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    /**
     * 通过类型查找枚举类
     * @param type 事件类型
     * @return
     */
    public static Optional<EventTypeEnum> getByType(int type) {
        for (EventTypeEnum em : EventTypeEnum.values()) {
            if (em.getType() == type) {
                return Optional.of(em);
            }
        }
        return Optional.empty();
    }
}
