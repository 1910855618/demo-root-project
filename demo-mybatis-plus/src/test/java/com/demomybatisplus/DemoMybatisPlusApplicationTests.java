package com.demomybatisplus;

import com.demomybatisplus.entity.User;
import com.demomybatisplus.mapper.UserMapper;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@Log4j2
@SpringBootTest
class DemoMybatisPlusApplicationTests {
    @Autowired
    private UserMapper userMapper;

    @Test
    void mybatisPlus() {
        List<User> users = userMapper.selectList(null);
        log.info("users: {}", users);
        users.forEach(item -> log.info("name: {}", item::getName));
    }

}
