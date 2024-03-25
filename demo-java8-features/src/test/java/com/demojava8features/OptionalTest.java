package com.demojava8features;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Log4j2
@SpringBootTest
public class OptionalTest {
    @Test
    public void optionalTest() {
        log.info("empty 创建 optional 空对象：{}", Optional.empty());
        log.info("of 创建 optional 非空对象：{}", Optional.of("Hello! Good morning! Have nice day!"));
        log.info("ofNullable 创建一个可为空的对象：{}", Optional.ofNullable("I very like my teacher!"));
        Map<String, String> map = new HashMap<>();
        map.put("name", "Anna");
        log.info("获取 optional 中的值：{}", Optional.of(map).map(data -> data.get("name")));
        log.info("使用 orElse 取值：{}", Optional.of("She's Good a teacher!").orElse("Really?"));
        log.info("使用 orElseGet 取值：{}", Optional.of("you're Good a teacher!").orElseGet(() -> "Really?"));
        log.info("使用 orElseThrow 取值：{}", Optional.of("I'm Good a teacher!").orElseThrow(IllegalArgumentException::new));
        // 使用 ifPresent 将 optional 对象中的值打印
        Optional.of("Everything is possible!").ifPresent(log::info);
        // 使用 filter 过滤字符串
        log.info("使用 filter 过滤字符串：{}", Optional.of("I like all-in!").filter(s -> s.contains("all")));
        // 简化写法
        // 1.7 写法
        log.info("1.7 optional 写法：{}", nameUpperCase(map));
        // 1.8 写法
        log.info("1.8 optional 写法：{}", Optional.of(map).map(data -> data.get("name")).map(String::toUpperCase).orElse(null));
        try {
            // 1.7 写法
            if (map != null) {
                throw new RuntimeException("用户名已存在");
            }
        } catch (RuntimeException e) {
            log.error(e);
        }
        try {
            // 1.8 写法
            Optional.ofNullable(map).ifPresent(item -> {
                throw new RuntimeException("用户名已存在");
            });
        } catch (RuntimeException e) {
            log.error(e);
        }
    }

    public String nameUpperCase(Map<String, String> map) {
        if(map != null) {
            String name = map.get("name");
            if(name != null) {
                return name.toUpperCase();
            } else {
                return null;
            }
        } else {
            return null;
        }
    }
}
