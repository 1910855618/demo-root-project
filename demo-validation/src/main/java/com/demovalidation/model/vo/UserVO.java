package com.demovalidation.model.vo;

import com.demovalidation.annotation.Radio;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserVO {
    @NotBlank(message = "不能为空") // 被注释的字符串必须非空，去除首尾空格后长度必须大于 0
    private String name;
    @Pattern(regexp = "^1[3456789]\\d{9}$", message = "格式不正确") // 被注释的元素必须符合指定的正则表达式
    @NotNull(message = "不能为null") // 被注释的元素必须不为 null
    private String phone;
    @NotEmpty(message = "不能为空") // 被注释的字符串、集合、Map、数组必须非空
    private List<String> hobby;
    @Email(message = "格式不正确") // 被注释的元素必须是一个合法的电子邮件地址
    private String email;

    @Size(min = 1, max = 3, message = "必须大于等于1小于等于3") // 被注释的字符串、集合、Map、数组的大小必须在指定的范围内
    private List<String> title;
    @Max(value = 200, message = "必须小于等于200") // 被注释的元素必须是一个数字，其值必须小于或等于指定的最大值
    private Integer age;
    @Min(value = 1, message = "必须大于等于1") // 被注释的元素必须是一个数字，其值必须大于或等于指定的最小值
    private Integer grade;
    @DecimalMax(value = "1000", message = "必须小于等于1000") // 被注释的元素必须是一个数字，其值必须小于或等于指定的最大值
    private Integer studentClass;
    @DecimalMin(value = "1", message = "必须大于等于1") // 被注释的元素必须是一个数字，其值必须大于或等于指定的最小值
    private Integer group;
    @Digits(integer = 3, fraction = 2, message = "整数位不能超3位，小数位不能超2位") // 被注释的元素必须是数字，并且它的值必须在指定的范围内
    private BigDecimal number;
    @Positive(message = "必须是整数") // 被注释的元素必须是一个正数
    private Integer positive;
    @PositiveOrZero(message = "必须是大于等于0的整数") // 被注释的元素必须是一个非负数
    private Integer positiveOrZero;
    @Negative(message = "必须是负数") // 被注释的元素必须是一个负数
    private Integer negative;
    @NegativeOrZero(message = "必须是小于等于0的负数") // 被注释的元素必须是一个非正数
    private Integer negativeOrZero;

    @Past(message = "不能是当前或者未来时间") // 被注释的元素必须是一个过去的日期
    private LocalDateTime birthday;
    @Future(message = "不能是当前或者过去时间") // 被注释的元素必须是一个将来的日期
    private LocalDateTime future;
    @PastOrPresent(message = "不能是未来的日期") // 被注释的元素必须是一个过去或现在的日期
    private LocalDateTime pastOrPresent;
    @FutureOrPresent(message = "不能是过去日期") // 被注释的元素必须是一个将来或现在的日期
    private LocalDateTime futureOrPresent;

    @AssertTrue(message = "必须是true") // 被注释的元素必须为 true
    private Boolean isStudent;
    @AssertFalse(message = "必须是false") // 被注释的元素必须为 false
    private Boolean isTeacher;
    @Null(message = "必须是null") // 被注释的元素必须为 null
    private String isNull;

    @Radio(value = {"Java", "Python", "C++", "C#", "Go"}, message = "只能选择可选项中的一项") // 自定义验证
    private String developmentLanguage;
}
