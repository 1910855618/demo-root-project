package com.demopool2.controller;

import com.demopool2.manager.MyObjectManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class TestController {
    @Resource
    private MyObjectManager manager;

    @GetMapping("/print")
    public String print() {
        manager.printStr();
        return "ok";
    }
}
