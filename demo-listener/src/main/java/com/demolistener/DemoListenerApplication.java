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

@EnableAsync // 开启异步
@SpringBootApplication
public class DemoListenerApplication {
    // https://mrbird.cc/%E6%B7%B1%E5%85%A5%E7%90%86%E8%A7%A3Spring%E4%BA%8B%E4%BB%B6%E5%8F%91%E5%B8%83%E4%B8%8E%E7%9B%91%E5%90%AC.html
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(DemoListenerApplication.class);
        // 注册监听
        app.addListeners(new SpringApplicationCustomListener());
        ApplicationContext context = app.run(args);
        // 发布自定义事件
        context.publishEvent(new CustomEvent("Hello!"));
        // 发布自定义异步事件
        context.publishEvent(new AsyncCustomEvent(""));
        // 发布排序事件
        context.publishEvent(new OrderedEvent(""));
        // 配合 SpEL 表达式发布事件
        SpELCustomEvent event = new SpELCustomEvent("");
        event.setFlag(true);
        context.publishEvent(event);
    }

}
