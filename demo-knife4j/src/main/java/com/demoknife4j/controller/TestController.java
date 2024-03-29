package com.demoknife4j.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "测试模块")
@RestController
public class TestController {
    @ApiOperation(value = "测试接口", notes = "用于测试")
    @GetMapping("/")
    public String hello() {
        return "Hello!";
    }
}
