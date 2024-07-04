package com.demoscheduled;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling // 开启定时任务功能
public class DemoScheduledApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoScheduledApplication.class, args);
    }

}
