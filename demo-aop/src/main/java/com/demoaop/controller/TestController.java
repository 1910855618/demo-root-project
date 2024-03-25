package com.demoaop.controller;

import com.demoaop.annotation.Log;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @Log("run one method")
    @GetMapping("/one")
    public String one() {
        return "request one method";
    }

    @Log("run two method")
    @GetMapping("/two")
    public String two() throws InterruptedException {
        Thread.sleep(2000);
        return "request two method";
    }

    @Log("run three method")
    @GetMapping("/three")
    public String three(@RequestParam("name") String name) {
        return "request three method";
    }
}
