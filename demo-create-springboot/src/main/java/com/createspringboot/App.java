package com.createspringboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App {
    public static void main(String[] args) {
        // 不做任何处理直接使用 SpringApplication.run 启动
//        SpringApplication.run(App.class, args);
        // 获取 SpringApplication 对象，可以用该对象做一些相应处理在调用 run 方法启动
        SpringApplication app = new SpringApplication(App.class);
        app.run(args);
    }
}
