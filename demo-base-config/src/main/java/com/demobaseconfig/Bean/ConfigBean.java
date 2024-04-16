package com.demobaseconfig.Bean;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix="author")
public class ConfigBean {
//    @Value("${author.name}")
    private String name;
//    @Value("${author.age}")
    private Integer age;
    private String introduce;
}
