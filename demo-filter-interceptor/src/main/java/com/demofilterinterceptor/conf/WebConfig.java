package com.demofilterinterceptor.conf;

import com.demofilterinterceptor.filter.Test2Filter;
import com.demofilterinterceptor.interceptor.TestInterceptor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.annotation.Resource;
import javax.servlet.Filter;
import java.util.ArrayList;
import java.util.Collections;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {
    @Resource
    private TestInterceptor testInterceptor;

    @Bean
    public FilterRegistrationBean<Filter> test2Filter() {
        FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(new Test2Filter());
        filterRegistrationBean.setUrlPatterns(new ArrayList<>(Collections.singletonList("/*")));
        return filterRegistrationBean;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(testInterceptor);
    }
}
