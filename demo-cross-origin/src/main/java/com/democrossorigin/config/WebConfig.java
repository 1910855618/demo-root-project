package com.democrossorigin.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    // WebConfig 添加跨域映射处理跨域
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // 允许那些接口能被跨域访问，/** 表示所有
                .allowedOrigins("*") // 允许那些来源地址能跨域访问，* 表示所有
                .allowedMethods("GET"); // 允许那些请求方式能被跨域访问，* 表示所有
    }

    // 过滤器处理跨域
    // 创建并配置了一个 FilterRegistrationBean 过滤器实例
    @Bean
    public FilterRegistrationBean<?> corsFilter() {
        // 创建一个基于 URL 的 CORS 配置源
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        // 创建一个 CORS 配置对象
        CorsConfiguration config = new CorsConfiguration();
        // 设置是否允许发送凭证（如 cookies 和 HTTP 认证信息）到目标服务器
        config.setAllowCredentials(true);
        // 允许那些来源地址能跨域访问，* 表示所有
        config.addAllowedOriginPattern("*");
        // 允许那些接口能被跨域访问，/** 表示所有
        source.registerCorsConfiguration("/**", config);
        // 创建一个 FilterRegistrationBean 过滤器实例，将 CorsFilter 过滤器作为参数传入，并将上面配置的 CORS 配置源与之关联
        FilterRegistrationBean<?> bean = new FilterRegistrationBean<>(new CorsFilter(source));
        // 设置优先级，参数越小优先级越高，0 表示最高优先级
        bean.setOrder(0);
        // 将配置好的 FilterRegistrationBean 返回给 Spring 容器，以便 Spring 能够在启动时将其注册为一个过滤器，从而处理所有传入的跨域请求
        return bean;
    }
}
