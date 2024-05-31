package com.demorabbitmq.controller;

import com.demorabbitmq.service.ProducerService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@RestController
public class TestController {
    @Resource
    private ProducerService producerService;

    @Resource
    private ObjectMapper objectMapper;

    @GetMapping("/hello")
    public Map<String, Object> hello() {
        Map<String, Object> res = new HashMap<>();
        producerService.send("hello!");
        res.put("timestamp", Instant.now().toEpochMilli());
        res.put("message", "success");
        res.put("code", 200);
        return res;
    }

    @GetMapping("/work")
    public Map<String, Object> work() throws JsonProcessingException {
        Map<String, Object> res = new HashMap<>();
        Map<String, Object> message = new HashMap<>();
        message.put("name", "luhang");
        message.put("gender", "man");
        message.put("age", 500);
        producerService.sendWork(message);
        res.put("timestamp", Instant.now().toEpochMilli());
        res.put("message", "success");
        res.put("code", 200);
        return res;
    }

    @GetMapping("/publish")
    public Map<String, Object> publish() throws JsonProcessingException {
        Map<String, Object> res = new HashMap<>();
        Map<String, Object> message = new HashMap<>();
        message.put("name", "Annie");
        message.put("gender", "woman");
        message.put("age", 1500);
        producerService.sendSubscribe(message);
        res.put("timestamp", Instant.now().toEpochMilli());
        res.put("message", "success");
        res.put("code", 200);
        return res;
    }

    @GetMapping("/router")
    public Map<String, Object> router() throws JsonProcessingException {
        Map<String, Object> res = new HashMap<>();
        Map<String, Object> message = new HashMap<>();
        message.put("name", "Emma");
        message.put("gender", "woman");
        message.put("age", 23);
        producerService.sendRouter(message);
        res.put("timestamp", Instant.now().toEpochMilli());
        res.put("message", "success");
        res.put("code", 200);
        return res;
    }

    @GetMapping("/topic")
    public Map<String, Object> topic() throws JsonProcessingException {
        Map<String, Object> res = new HashMap<>();
        Map<String, Object> message = new HashMap<>();
        message.put("name", "Jack");
        message.put("gender", "man");
        message.put("age", 33);
        producerService.sendTopic(message);
        res.put("timestamp", Instant.now().toEpochMilli());
        res.put("message", "success");
        res.put("code", 200);
        return res;
    }

    @GetMapping("/rpc")
    public Map<String, Object> rpc() throws JsonProcessingException {
        Map<String, Object> res = new HashMap<>();
        Map<String, Object> message = new HashMap<>();
        message.put("name", "Lilia");
        message.put("gender", "woman");
        message.put("age", 1000);
        res.put("timestamp", Instant.now().toEpochMilli());
        res.put("message", "success");
        res.put("code", 200);
        res.put("data", objectMapper.readValue(producerService.sendRPC(message).toString(), Map.class));
        return res;
    }
}
