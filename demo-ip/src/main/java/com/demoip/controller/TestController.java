package com.demoip.controller;

import com.demoip.utils.IPUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Log4j2
@RestController
public class TestController {
    @GetMapping("/")
    public Map<?, ?> analysisUserAgent(HttpServletRequest request, String ip) {
        ip = Optional.ofNullable(ip).orElse(Optional.ofNullable(IPUtil.getClientIP(request)).orElse(""));
        if(ip.isEmpty()) {
            ip = "0.0.0.0";
        }
        List<String> addressList = IPUtil.parse(ip);
        log.info("IP: {}, 属地: {}", ip, addressList);
        Map<String, Object> res = new HashMap<>();
        res.put("ip", ip);
        res.put("country", addressList.get(0));
        res.put("region", addressList.get(1));
        res.put("province", addressList.get(2));
        res.put("city", addressList.get(3));
        res.put("isp", addressList.get(4));
        return res;
    }
}
