package com.demoasync;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class DemoAsyncApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoAsyncApplication.class, args);
    }

}
