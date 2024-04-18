package com.demotest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@Controller
public class TestController {
    @RequestMapping("/hello")
    @ResponseBody
    public String hello(String name) {
        return String.join(" ", "Hello! Welcome", name, "to test!");
    }

    @RequestMapping("/user/{id}")
    @ResponseBody
    public Map<String, Object> user(@PathVariable String id) {
        Map<String, Object> res = new HashMap<>();
        res.put("code", 200);
        res.put("timestamp", Instant.now().toEpochMilli());
        res.put("message", "success");
        res.put("data", String.join(" ", "userId:", id));
        return res;
    }

    @RequestMapping("/fileUpload")
    @ResponseBody
    public Map<String, Object> fileUpload(@RequestParam("file") MultipartFile file) throws IOException {
        byte[] bytes = file.getBytes();
        Map<String, Object> res = new HashMap<>();
        res.put("code", 200);
        res.put("timestamp", Instant.now().toEpochMilli());
        res.put("message", "success");
        res.put("data", new String(bytes, StandardCharsets.UTF_8));
        return res;
    }

    @GetMapping("/saveUser")
    @ResponseBody
    public Map<String, Object> saveUser(String name, Integer age, String job) {
        Map<String, Object> res = new HashMap<>();
        res.put("code", 200);
        res.put("timestamp", Instant.now().toEpochMilli());
        res.put("message", "success");
        res.put("data", String.join("-", name, age.toString(), job));
        return res;
    }

    @PostMapping("/saveUser")
    @ResponseBody
    public Map<String, Object> saveUser(@RequestBody Map<String, Object> body) {
        Map<String, Object> res = new HashMap<>();
        res.put("code", 200);
        res.put("timestamp", Instant.now().toEpochMilli());
        res.put("message", "success");
        res.put("data", body);
        return res;
    }

    @RequestMapping("/index")
    public String index(HttpServletRequest request, Model model) {
        model.addAttribute("name", "luhang");
        System.out.println("session name: " + request.getSession().getAttribute("name"));
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("name".equals(cookie.getName())) {
                    System.out.println("cookie name: " + cookie.getValue());
                }
            }
        }
        return "/index.html";
    }

    @RequestMapping("/home")
    public String home() {
        return "redirect:/index.html";
    }

    @RequestMapping("/xml")
    @ResponseBody
    public String xml() {
        return "<body>body</body>";
    }

    @RequestMapping("/json")
    @ResponseBody
    public String json() {
        return "{\"name\": \"luhang\"}";
    }
}
