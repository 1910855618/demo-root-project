package com.demopackage;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * ServletInitializer 启动类
 * 生产 war 包必须继承 SpringBootServletInitializer 将 Application 作为源添加到 SpringApplicationBuilder 中
 * */
public class ServletInitializer extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(DemoPackageApplication.class);
    }
}
