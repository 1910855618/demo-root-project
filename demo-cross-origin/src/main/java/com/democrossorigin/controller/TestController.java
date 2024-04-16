package com.democrossorigin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {
    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @GetMapping("/hello")
    @ResponseBody
    @CrossOrigin(value = "*") // 注解处理跨域
    public String hello() {
        return "Hello! Have a nice day!";
    }
}
