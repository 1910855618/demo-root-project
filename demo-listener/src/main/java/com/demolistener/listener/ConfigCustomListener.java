package com.demolistener.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.SpringApplicationEvent;
import org.springframework.context.ApplicationListener;

/**
 * 使用配置文件配置监听
 * */
@Slf4j
public class ConfigCustomListener implements ApplicationListener<SpringApplicationEvent> {
    // 通过实现 ApplicationListener.onApplicationEvent 监听 SpringApplicationEvent 事件
    @Override
    public void onApplicationEvent(SpringApplicationEvent event) {
        log.info("ConfigCustomListener event: {}", event.toString());
    }
}
