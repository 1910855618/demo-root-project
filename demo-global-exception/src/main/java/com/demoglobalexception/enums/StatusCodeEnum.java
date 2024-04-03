package com.demoglobalexception.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatusCodeEnum {

    SUCCESS(200, "操作成功"),

    NO_LOGIN(401, "用户未登录"),

    AUTHORIZED(403, "没有操作权限"),

    SYSTEM_ERROR(500, "系统异常"),

    FAIL(510, "操作失败");

    private final Integer code;

    private final String desc;

}
