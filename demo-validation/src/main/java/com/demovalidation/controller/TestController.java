package com.demovalidation.controller;

import com.demovalidation.model.vo.ResponseVO;
import com.demovalidation.model.vo.UserVO;
import lombok.extern.log4j.Log4j2;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.Instant;

@Log4j2
@RestController
@Validated
public class TestController {
    // 参数验证失败抛出 ConstraintViolationException 异常
    @GetMapping("/sendEmail")
    public ResponseVO sendEmail(@Email(message = "{validation.email.invalid}") String email,
                                @NotBlank(message = "{validation.content.required}") String content) {
        log.info("发送至：{}，内容为：{}", email, content);
        return ResponseVO.builder().code(200).message("success").timestamp(Instant.now().toEpochMilli()).build();
    }

    // 参数验证失败抛出 BindException 异常
    @GetMapping("/getUser")
    public ResponseVO getUser(@Valid UserVO userVO) {
        return ResponseVO.builder().code(200).message("success").timestamp(Instant.now().toEpochMilli()).data(userVO).build();
    }

    // 参数校验失败抛出 MethodArgumentNotValidException 异常
    @PostMapping("/addUser")
    public ResponseVO addUser(@Valid @RequestBody UserVO userVO) {
        return ResponseVO.builder().code(200).message("success").timestamp(Instant.now().toEpochMilli()).data(userVO).build();
    }
}