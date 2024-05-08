package com.demop6spy;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

@Slf4j
@SpringBootTest
class DemoP6spyApplicationTests {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    void test() {
        String sql = "select * from user";
        log.info("查询结果：{}", jdbcTemplate.queryForList(sql));
    }
}
