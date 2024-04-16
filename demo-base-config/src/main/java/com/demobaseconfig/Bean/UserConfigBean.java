package com.demobaseconfig.Bean;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;


@Data
@Component
@PropertySource("classpath:user.properties")
@ConfigurationProperties(prefix="user")
public class UserConfigBean {
    private String name;
    private Integer age;
}
