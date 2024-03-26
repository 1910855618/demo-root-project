package com.demoaop.controller;

import com.demoaop.annotation.Log;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Log4j2
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
    public String three(@RequestParam("name") String name, @RequestParam("age") Integer age) {
        return "request three method";
    }

    @Log("run four method")
    @PostMapping("/four")
    public Object four(@RequestBody Map<String, Object> params) {
        return params;
    }
}
