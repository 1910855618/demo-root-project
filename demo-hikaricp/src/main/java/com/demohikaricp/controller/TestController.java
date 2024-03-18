package com.demohikaricp.controller;

import com.demohikaricp.mapper.primary.UserMapper;
import com.demohikaricp.mapper.secondary.SecondaryUserMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

@RestController
public class TestController {
    @Resource
    private UserMapper userMapper;
    @Resource
    private SecondaryUserMapper secondaryUserMapper;

    @GetMapping("/getPrimaryInfo")
    public Map<String, Object> getPrimaryInfo() {
        return userMapper.queryAll();
    }

    @GetMapping("/getSecondaryInfo")
    public Map<String, Object> getSecondaryInfo() {
        return secondaryUserMapper.queryAll();
    }
}
