package com.demoglobalexception.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatusCodeEnum {
    SUCCESS(200, "operation success"),

    NO_LOGIN(401, "user is not logged in"),

    AUTHORIZED(403, "no permission to operate"),

    SYSTEM_ERROR(500, "system is abnormal"),

    FAIL(510, "operation failed");

    private final Integer code;

    private final String desc;
}
