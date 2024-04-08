package com.demoglobalexception.handler;

import com.demoglobalexception.enums.StatusCodeEnum;
import com.demoglobalexception.exception.BusinessException;
import com.demoglobalexception.model.vo.ResponseVO;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@Log4j2
@RestControllerAdvice
public class GlobalExceptionHandler {
    // 应为所有异常都继承至 Exception，在抛出的异常找不到对应的异常处理时，统一走 Exception 异常处理
    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseVO errorHandler(Exception e) {
        log.error(e);
        return ResponseVO.builder().timestamp(Instant.now().toEpochMilli()).code(StatusCodeEnum.SYSTEM_ERROR.getCode()).message(e.getMessage()).build();
    }

    @ExceptionHandler(value = RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseVO errorHandler(RuntimeException e) {
        log.error(e);
        return ResponseVO.builder().timestamp(Instant.now().toEpochMilli()).code(StatusCodeEnum.SYSTEM_ERROR.getCode()).message(e.getMessage()).build();
    }

    @ExceptionHandler(value = BusinessException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseVO errorHandler(BusinessException e) {
        log.error(e);
        return ResponseVO.builder().timestamp(Instant.now().toEpochMilli()).code(e.getCode()).message(e.getMessage()).build();
    }
}
