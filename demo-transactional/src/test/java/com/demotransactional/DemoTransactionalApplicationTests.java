package com.demotransactional;

import com.demotransactional.entity.User;
import com.demotransactional.service.UserService;
import com.demotransactional.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class DemoTransactionalApplicationTests {
    // 借阅 https://juejin.cn/post/6844903608224333838
    @Autowired
    private UserService userService;

    @Test
    void testTransactional() {
        // 发生异常事会触发事务回滚
//        userService.testSaveProgrammatic(User.builder().name("test").age(18).gender(0).build());
//        userService.testSaveProgrammaticManager(User.builder().name("test").age(18).gender(0).build());
//        userService.testSave(User.builder().name("test").age(18).gender(0).build());
        // 该方法没有使用 @Transactional 注解，事务不会生效
        userService.testSaveWrap(User.builder().name("test").age(18).gender(0).build());
    }
}
