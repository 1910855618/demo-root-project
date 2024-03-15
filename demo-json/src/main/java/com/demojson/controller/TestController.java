package com.demojson.controller;

import com.demojson.entity.User;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;


@Log4j2
@RestController
public class TestController {
    @Autowired
    private ObjectMapper objectMapper;

    @RequestMapping("/getUser")
    public User getUser() {
        return User.builder()
                .name("luhang")
                .age(24)
                .birthday(LocalDateTime.now())
                .build();
    }

    @RequestMapping("/errorDemo")
    public String errorDemo() throws IOException {
        String jsonStr = "[{\"name\":\"luhang\",\"age\":26},{\"name\":\"scott\",\"age\":27}]";
        JavaType type = objectMapper.getTypeFactory().constructParametricType(List.class, User.class);
        List<User> list = objectMapper.readValue(jsonStr, type);
        StringBuilder msg = new StringBuilder();
        for (User user : list) {
            msg.append(user.getName());
        }
        return msg.toString();
    }

    @RequestMapping("/serialization")
    public String serialization() {
        try {
            return objectMapper.writeValueAsString(User.builder()
                    .name("luhang")
                    .age(24)
                    .birthday(LocalDateTime.now())
                    .build());
        } catch (Exception e) {
            log.error(e);
        }
        return null;
    }

    @RequestMapping("/deserialization")
    public String deserialization() {
        try {
            String jsonString = "{" +
                    "   \"name\": \"kunKun\", " +
                    "   \"age\": 34, " +
                    "   \"hobby\": {" +
                    "       \"first\": \"sing\", " +
                    "       \"second\": \"jump\", " +
                    "       \"third\": \"basketball\", " +
                    "       \"array\": [\"one\", \"two\", \"three\"]" +
                    "   }," +
                    "   \"mockers\": [{\"name\": \"lilia\", \"age\": 18}, {\"name\": \"mary\", \"age\": 18}]" +
                    "}";
            JsonNode root = objectMapper.readTree(jsonString);

            // 解析 JSON
            String name = root.get("name").asText();
            int age = root.get("age").asInt();

            // JSON 多级解析
            JsonNode hobby = root.get("hobby");
            String first = hobby.get("first").asText();

            // 解析 JSON 数组
            JsonNode array = hobby.get("array");
            String one = array.get(0).asText();

            // 将 JSON 映射到 java 对象
            User user = objectMapper.readValue(jsonString, User.class);
            log.info("映射出的 user 结果: {}", user);

            // 映射集合
            JsonNode mockers = root.get("mockers");
            JavaType type = objectMapper.getTypeFactory().constructParametricType(List.class, User.class);
            List<User> list = objectMapper.readValue(mockers.toString(), type);
            log.info("映射出的 list 结果: {}", list);

            // 将 java 对象序列化为 JSON
            String json = objectMapper.writeValueAsString(list);
            log.info("序列化的 json 结果: {}", json);

            return "{\"name\": \"#{name}\", \"age\": #{age}, \"first\": \"#{first}\", \"array\": \"#{array}\"}"
                    .replace("#{name}", name)
                    .replace("#{age}", String.valueOf(age))
                    .replace("#{first}", first)
                    .replace("#{array}", one);
        } catch (Exception e) {
            log.error(e);
        }
        return null;
    }

    @RequestMapping("/customizeDeserialization")
    public String customizeDeserialization() {
        try {
            String jsonString = "{\"account-type\":\"kunKun\"}";
            User user = objectMapper.readValue(jsonString, User.class);
            return user.getAccountType();
        } catch (Exception e) {
            log.error(e);
        }
        return null;
    }

    // 只显示 UserNameView 组下的属性
    @JsonView(User.UserNameView.class)
    @RequestMapping("/getUserName")
    public User getUserName() {
        return User.builder()
                .name("luhang")
                .age(24)
                .birthday(LocalDateTime.now())
                .build();
    }

    // 显示 AllView 组下的属性，由于 AllView 继承了 UserNameView 所以 UserNameView 也会被显示
    @JsonView(User.AllView.class)
    @RequestMapping("/getAllUser")
    public User getAllUser() {
        return User.builder()
                .name("luhang")
                .age(24)
                .birthday(LocalDateTime.now())
                .build();
    }
}
