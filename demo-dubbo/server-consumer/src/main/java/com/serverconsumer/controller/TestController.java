package com.serverconsumer.controller;

import com.commonapi.service.TestService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @DubboReference
    private TestService testService;

    @GetMapping("/hello")
    public String hello(String message) {
        return this.testService.hello(message);
    }
}
