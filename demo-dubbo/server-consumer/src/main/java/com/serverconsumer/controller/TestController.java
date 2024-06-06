package com.serverconsumer.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.commonapi.service.TestService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @Reference
    private TestService testService;

    @GetMapping("/hello")
    public String hello(String message) {
        return this.testService.hello(message);
    }
}
