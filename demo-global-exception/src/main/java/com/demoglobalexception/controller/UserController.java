package com.demoglobalexception.controller;

import com.demoglobalexception.model.vo.ResponseVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @GetMapping("/get/{id:\\d+}")
    public ResponseVO getUser(@PathVariable String id) {
        throw new RuntimeException("user not exist");
    }
}

