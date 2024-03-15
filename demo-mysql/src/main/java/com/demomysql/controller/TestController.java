package com.demomysql.controller;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
public class TestController {
    // 注入 spring jdbc
    @Resource
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/getUsers")
    public List<Map<String, Object>> getUsers() {
        return jdbcTemplate.queryForList("select * from user");
    }
}
