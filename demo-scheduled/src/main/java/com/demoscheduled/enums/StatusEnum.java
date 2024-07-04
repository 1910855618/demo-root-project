package com.demoscheduled.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatusEnum {
    OFF(0, "关闭"),
    ON(1, "开启");

    @EnumValue
    private final Integer code;
    private final String desc;
}
