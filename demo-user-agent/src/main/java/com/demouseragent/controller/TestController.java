package com.demouseragent.controller;

import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.OperatingSystem;
import eu.bitwalker.useragentutils.UserAgent;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Log4j2
@RestController
public class TestController {
    @GetMapping("/")
    public Map<?, ?> analysisUserAgent(HttpServletRequest request) {
        String userAgentString = request.getHeader("User-Agent");
        log.info("User-Agent: {}", userAgentString);

        //解析 agent 字符串
        UserAgent userAgent = UserAgent.parseUserAgentString(userAgentString);
        // 获取浏览器对象
        Browser browser = userAgent.getBrowser();
        // 获取操作系统对象
        OperatingSystem operatingSystem = userAgent.getOperatingSystem();

        Map<String, Object> res = new HashMap<>();
        res.put("browserName", browser.getName());
        res.put("browserType", browser.getBrowserType());
        res.put("browserGroup", browser.getGroup());
        res.put("browserManufacturer", browser.getManufacturer());
        res.put("browserRenderingEngine", browser.getRenderingEngine());
        res.put("browserBrowserVersion", userAgent.getBrowserVersion());
        res.put("operatingSystemName", operatingSystem.getName());
        res.put("operatingSystemDeviceType", operatingSystem.getDeviceType());
        res.put("operatingSystemGroup", operatingSystem.getGroup());
        res.put("operatingSystemManufacturer", operatingSystem.getManufacturer());

        log.info("浏览器名: {}", res.get("browserName"));
        log.info("浏览器类型: {}", res.get("browserType"));
        log.info("浏览器家族: {}", res.get("browserGroup"));
        log.info("浏览器生产厂商: {}", res.get("browserManufacturer"));
        log.info("浏览器使用的渲染引擎: {}", res.get("browserRenderingEngine"));
        log.info("浏览器版本: {}", res.get("browserBrowserVersion"));
        log.info("操作系统名: {}", res.get("operatingSystemName"));
        log.info("访问设备类型: {}", res.get("operatingSystemDeviceType"));
        log.info("操作系统家族: {}", res.get("operatingSystemGroup"));
        log.info("操作系统生产厂商: {}", res.get("operatingSystemManufacturer"));

        return res;
    }
}
