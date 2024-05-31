package com.demorabbitmq;

import com.demorabbitmq.service.ProducerService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class DemoRabbitmqApplicationTests {
    @Resource
    private ProducerService producerService;

    @Test
    void contextLoads() {
        producerService.send("你好");
    }

}
