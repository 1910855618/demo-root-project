package com.demofilterinterceptor.filter;

import lombok.extern.log4j.Log4j2;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.time.Instant;

@Log4j2
@Order(1) // value 值越小优先级越高
@Component
@WebFilter(urlPatterns = {"/*"})
public class TestFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("filter init");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("filter body execute");
        long startTime = Instant.now().toEpochMilli();
        filterChain.doFilter(servletRequest, servletResponse);
        log.info("cost time: {}ms", Instant.now().toEpochMilli() - startTime);
        log.info("filter body end");
    }

    @Override
    public void destroy() {
        log.info("filter destroy");
    }
}
