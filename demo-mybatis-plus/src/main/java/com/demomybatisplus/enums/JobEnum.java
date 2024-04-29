package com.demomybatisplus.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum JobEnum {
    MONITOR(0, "班长"),
    STUDY_COMMITTEE(1, "学习委员"),
    DISCIPLINARY_COMMITTEE(2, "纪律委员"),
    COMMISSAR_OF_HEALTH(3, "卫生委员");

    // 告诉 mybatis plus 入库时使用 code 字段
    @EnumValue
    private final Integer code;
    private final String desc;
}
