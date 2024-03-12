package com.demooauth201;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;

@SpringBootApplication
public class DemoOauth201Application {

    public static void main(String[] args) {
        SpringApplication.run(DemoOauth201Application.class, args);
    }

}
