package com.demoknife4j.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Configuration
public class Knife4jConfig {
    // 创建一个 swagger 的 bean 实例，Docket 是 Knife4j 中用来配置 Swagger 文档的核心类，它包含了许多配置选项来定制 API 文档的生成
    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                // 指定 Swagger 文档使用 HTTPS 协议
                .protocols(Collections.singleton("http"))
                // 指定 Swagger 文档 Host 地址
                .host("http://127.0.0.1:8080/")
                // 设置 Swagger 文档的基本信息
                .apiInfo(apiInfo())
                // 配置接口信息，设置扫描接口
                .select()
                /*
                 * RequestHandlerSelectors
                 *      .any() // 扫描全部的接口，默认
                 *      .none() // 全部不扫描
                 *      .basePackage("com.mike.server") // 扫描指定包下的接口，最为常用
                 *      .withClassAnnotation(RestController.class) // 扫描带有指定注解的类下所有接口
                 *      .withMethodAnnotation(PostMapping.class) // 扫描带有只当注解的方法接口
                 */
                // 此包路径下的类，才生成接口文档
                .apis(RequestHandlerSelectors.basePackage("com.demoknife4j.controller"))
                // 加了 RestController 注解的类，才生成接口文档
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                /*
                 * PathSelectors
                 *      .any() // 满足条件的路径，该断言总为true
                 *      .none() // 不满足条件的路径，该断言总为false（可用于生成环境屏蔽 swagger）
                 *      .ant("/user/**") // 满足字符串表达式路径
                 *      .regex("") // 符合正则的路径
                 */
                // 指定了包含所有路径的接口都会被包含在生成的 Swagger 文档中
                .paths(PathSelectors.any())
                .build()
                // 安全模式
                .securitySchemes(securitySchemes())
                // 安全上下文
                .securityContexts(securityContexts())
                .pathMapping("/");
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                // 项目标题
                .title("demo-knife4j")
                // 项目描述
                .description("一个测试案例")
                // 服务条款说明信息链接
                .termsOfServiceUrl("https://luhang.fun/api")
                // 许可证
                .license("自己给自己颁发个许可吧")
                // 许可证链接
                .licenseUrl("https://luhang.fun")
                // 项目联系人信息（名称、网址、邮箱）
                .contact(new Contact("luhang", "https://luhang.fun", "3105808192@qq.com"))
                // 项目版本号
                .version("1.0")
                .build();
    }

    /**
     * 安全模式，这里指定 token 通过 Authorization 头请求头传递
     */
    private List<SecurityScheme> securitySchemes() {
        List<SecurityScheme> apiKeyList = new ArrayList<>();
        apiKeyList.add(new ApiKey("Authorization", "Authorization", "header"));
        return apiKeyList;
    }

    /**
     * 安全上下文
     */
    private List<SecurityContext> securityContexts() {
        List<SecurityContext> securityContexts = new ArrayList<>();
        securityContexts.add(
                SecurityContext.builder()
                        .securityReferences(defaultAuth())
                        .operationSelector(o -> o.requestMappingPattern().matches("/.*"))
                        .build());
        return securityContexts;
    }

    /**
     * 默认的全局鉴权策略
     */
    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        List<SecurityReference> securityReferences = new ArrayList<>();
        securityReferences.add(new SecurityReference("Authorization", authorizationScopes));
        return securityReferences;
    }
}
