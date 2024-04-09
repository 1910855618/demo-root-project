package com.demofilterinterceptor.controller;

import com.demofilterinterceptor.model.vo.ResponseVO;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

@Log4j2
@RestController
@RequestMapping("/user")
public class UserController {
    @GetMapping("/get/{id:\\d+}")
    public ResponseVO get(@PathVariable Integer id) {
        log.info("execute controller method query user ID: {}", id);
        if(id == 0) {
            throw new RuntimeException("user not exist");
        }
        return ResponseVO.builder().timestamp(Instant.now().toEpochMilli()).code(200).message("success").build();
    }
}
