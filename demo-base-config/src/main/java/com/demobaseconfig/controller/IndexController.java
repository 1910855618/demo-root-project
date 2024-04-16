package com.demobaseconfig.controller;

import com.demobaseconfig.Bean.ConfigBean;
import com.demobaseconfig.Bean.UserConfigBean;
import com.demobaseconfig.Bean.XmlConfigBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class IndexController {
    @Resource
    private ConfigBean configBean;
    @Resource
    private UserConfigBean userConfigBean;
    @Resource
    private XmlConfigBean xmlConfigBean;

    @GetMapping("/")
    public String index() {
//        return String.join("", "I'm ", configBean.getName(), ", ", configBean.getAge().toString(), " years old.");
        return configBean.getIntroduce();
    }

    @GetMapping("/getUser")
    public String getUser() {
        return String.join("", "I'm ", userConfigBean.getName(), ", ", userConfigBean.getAge().toString(), " years old.");
    }

    @GetMapping("/getXml")
    public String getXml() {
        return String.join("", "I'm ", xmlConfigBean.getName(), ", ", xmlConfigBean.getAge().toString(), " years old.");
    }
}
