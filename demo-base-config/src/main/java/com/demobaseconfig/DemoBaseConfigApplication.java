package com.demobaseconfig;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource({"classpath:user.xml"})
public class DemoBaseConfigApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(DemoBaseConfigApplication.class);
        // 启动时关闭 banner
        app.setBannerMode(Banner.Mode.OFF);
        // 禁止启动 jar 包时修改配置文件类容，列如：java -jar xxx.jar --server.port=8081，server.port 将无法被修改
        app.setAddCommandLineProperties(false);
        app.run(args);
    }

}
