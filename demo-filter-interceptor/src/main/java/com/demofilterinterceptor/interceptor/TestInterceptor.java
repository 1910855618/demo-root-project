package com.demofilterinterceptor.interceptor;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.Instant;

@Log4j2
@Component
public class TestInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("before interception");
        request.setAttribute("start", Instant.now().toEpochMilli());
        log.info("handler: {}",handler);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("interceptor execute");
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
        log.info("[interceptor] cost time: {}ms", Instant.now().toEpochMilli() - (long) request.getAttribute("start"));
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.info("after interception");
        log.info("[interceptor] cost time: {}ms", Instant.now().toEpochMilli() - (long) request.getAttribute("start"));
        if(ex != null) {
            log.error(ex);
        }
    }
}
