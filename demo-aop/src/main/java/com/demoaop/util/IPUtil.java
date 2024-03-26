package com.demoaop.util;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class IPUtil {
    public static String getIpAddr(HttpServletRequest request) {
        String ip = Optional.ofNullable(request.getHeader("x-forwarded-for")).orElse("");
        if (ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = Optional.ofNullable(request.getHeader("Proxy-Client-IP")).orElse("");
        }
        if (ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = Optional.ofNullable(request.getHeader("WL-Proxy-Client-IP")).orElse("");
        }
        if (ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return "0:0:0:0:0:0:0:1".equals(ip) ? "127.0.0.1" : ip;
    }
}
