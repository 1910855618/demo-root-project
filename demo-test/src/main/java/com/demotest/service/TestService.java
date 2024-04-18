package com.demotest.service;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Types;
import java.util.List;
import java.util.Map;

@Service
public class TestService {
    // 注入 spring jdbc
    @Resource
    private JdbcTemplate jdbcTemplate;

    // 查询操作
    public List<Map<String, Object>> getUserByName(String name) {
        return jdbcTemplate.queryForList("select * from user where name='"+name+"'");
    }

    // 添加操作
    public int save(Map<String, Object> params) {
        String sql = "insert into user(name, age, email, manager_id, create_time) values(?, ?, ?, ?, now())";
        Object[] args = {params.get("name"), params.get("age"), params.get("email"), params.get("manager_id")};
        int[] argsTypes = {Types.VARCHAR, Types.INTEGER, Types.VARCHAR, Types.INTEGER};
        return jdbcTemplate.update(sql, args, argsTypes);
    }
}
