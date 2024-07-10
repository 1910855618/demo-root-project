package com.demopool2.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

@Slf4j
@Getter
@Setter
@ToString
public class MyObject {
    private final String uid = UUID.randomUUID().toString();
    private volatile boolean valid = true;

    private String name;

    public void initialize() {
        log.info("初始化对象：{}", uid);
        valid = true;
    }

    public void destroy() {
        log.info("销毁对象：{}", uid);
        valid = false;
    }
}

