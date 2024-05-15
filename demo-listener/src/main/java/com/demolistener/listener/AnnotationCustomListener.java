package com.demolistener.listener;

import com.demolistener.listener.event.AsyncCustomEvent;
import com.demolistener.listener.event.CustomEvent;
import com.demolistener.listener.event.SpELCustomEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * 使用注解监听
 * */
@Slf4j
@Component
public class AnnotationCustomListener {
    //监听单个事件
    @EventListener
    public void listenerApplicationStartedEvent(ApplicationStartedEvent event) {
        log.info("应用启动完成, {}", event);
    }

    // 通过注解监听多个事件，Spring 中使用 ApplicationEvent 接口来表示一个事件，它是所有 Spring 事件的父类
    @EventListener(classes = {ApplicationStartedEvent.class, ApplicationReadyEvent.class})
    public void handleEvent(ApplicationEvent event) {
        if (event instanceof ApplicationStartedEvent) {
            log.info("监听到多个事件之一 ApplicationStartedEvent 事件, {}", event);
        }
        if (event instanceof ApplicationReadyEvent) {
            log.info("监听到多个事件之一 ApplicationReadyEvent 事件, {}", event);
        }
    }

    // 监听自定义事件
    @EventListener
    public void customEvent(CustomEvent event) {
        log.info("监听到自己发布的事件, {}", event);
        String str = (String) event.getSource();
        log.info("自定义事件中的值: {}", str);
    }

    // 开启单个异步事件
    @Async
    @EventListener
    public void asyncCustomEvent(AsyncCustomEvent event) {
        log.info("监听到自己发布的异步事件, {}", event);
    }

    // SpEL 表达式
    @EventListener(condition = "#event.flag")
    public void spELCustomEvent(SpELCustomEvent event) {
        log.info("监听到自己发布的 SpEL 表达式事件, {}", event);
    }
}
