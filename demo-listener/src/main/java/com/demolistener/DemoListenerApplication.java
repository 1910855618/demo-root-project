package com.demolistener;

import com.demolistener.listener.SpringApplicationCustomListener;
import com.demolistener.listener.event.AsyncCustomEvent;
import com.demolistener.listener.event.CustomEvent;
import com.demolistener.listener.event.OrderedEvent;
import com.demolistener.listener.event.SpELCustomEvent;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableAsync;

//@EnableAsync // 开启异步
@SpringBootApplication
public class DemoListenerApplication {
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(DemoListenerApplication.class);
        // 注册监听
        app.addListeners(new SpringApplicationCustomListener());
        ApplicationContext context = app.run(args);
        // 发布自定义事件
        context.publishEvent(new CustomEvent("Hello!"));
        // 发布自定义异步事件
        context.publishEvent(new AsyncCustomEvent(context));
        // 发布排序事件
        context.publishEvent(new OrderedEvent(context));
        // 配合 SpEL 表达式发布事件
        SpELCustomEvent event = new SpELCustomEvent(context);
        event.setFlag(true);
        context.publishEvent(event);
    }
}
