package com.demothymeleaf.controller;

import com.demothymeleaf.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class TestController {
    @RequestMapping("/account")
    public String account(Model m) {
        List<User> list = new ArrayList<>();
        list.add(User.builder().account("Luhang").name("颃").password("e10adc3949ba59abbe56e").accountType("超级管理员").phone("17777777777").build());
        list.add(User.builder().account("Mike").name("麦克").password("e10adc3949ba59abbe56e").accountType("管理员").phone("13444444444").build());
        list.add(User.builder().account("Jane").name("简").password("e10adc3949ba59abbe56e").accountType("运维人员").phone("18666666666").build());
        list.add(User.builder().account("Maria").name("玛利亚").password("e10adc3949ba59abbe56e").accountType("清算人员").phone("19999999999").build());
        m.addAttribute("accountList",list);
        return "account";
    }
}
