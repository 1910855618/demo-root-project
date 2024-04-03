package com.demoglobalexception.exception;

import com.demoglobalexception.enums.StatusCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BusinessException extends RuntimeException {
    private Integer code = StatusCodeEnum.FAIL.getCode();
    private final String message;

    public BusinessException(String message) {
        this.message = message;
    }

    public BusinessException(StatusCodeEnum statusCodeEnum) {
        this.code = statusCodeEnum.getCode();
        this.message = statusCodeEnum.getDesc();
    }
}
