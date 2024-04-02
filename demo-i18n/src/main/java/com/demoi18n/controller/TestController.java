package com.demoi18n.controller;

import com.demoi18n.model.vo.ResponseVO;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.time.Instant;

@RestController
public class TestController {
    @Resource
    private MessageSource messageSource;

    @GetMapping("/hello")
    public ResponseVO hello() {
        return ResponseVO.builder()
                .timestamp(Instant.now().toEpochMilli())
                .code(200)
                .message(messageSource.getMessage("hello", null, LocaleContextHolder.getLocale()))
                .build();
    }
}
