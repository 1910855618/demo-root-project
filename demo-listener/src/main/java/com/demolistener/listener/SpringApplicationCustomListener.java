package com.demolistener.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.SpringApplicationEvent;
import org.springframework.context.ApplicationListener;

/**
 * 通过启动类注册监听
 * */
@Slf4j
public class SpringApplicationCustomListener implements ApplicationListener<SpringApplicationEvent> {
    @Override
    public void onApplicationEvent(SpringApplicationEvent event) {
        log.info("SpringApplicationCustomListener event: {}", event.toString());
    }
}
