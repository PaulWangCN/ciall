package com.wangjx.ciall.event;

import com.wangjx.ciall.spring.SpringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @ClassName: EventHandlerManager
 * @Description: 事件处理类管理器
 * @Author: wangjiaxing
 * @Date: 2021/06/09 16:13
 * @Version 1.0
 */
@Component
@Import({
    SpringUtils.class,
})
public class EventHandlerManager {

    /**
     * 事件类型和事件处理器映射表缓存
     */
    private Map<EventTypeEnum, IEventHandler> handlerCache;

    @Autowired
    private SpringUtils springUtils;

    @PostConstruct
    private void init() {
        handlerCache = new HashMap<>();
        //扫描spring容器，获取所有指定注释的处理器类，并加载到映射表缓存中
        Map<String, IEventHandler> analysisResolverAwareMap = springUtils.getBeansWithAnnotation(EventHandler.class);
        analysisResolverAwareMap.values().forEach(v->{
            Set<EventTypeEnum> ems = v.listEventType();
            ems.forEach(e -> {
                System.out.println("处理器[" + v.getName() + "]已注册");
                handlerCache.putIfAbsent(e, v);
            });
        });
    }

    /**
     * 根据事件类型处理数据
     * @param em 事件类型枚举
     * @param msg 事件信息
     * @return
     */
    public String process(EventTypeEnum em, String msg) {
        return handlerCache.get(em).handle(msg);
    }

}
