package com.demovalidation.annotation;

import com.demovalidation.validator.RadioValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

// 表示该自定义注解只能应用于类的字段上
@Target(ElementType.FIELD)
// 表示这个注解保留到运行时
@Retention(RetentionPolicy.RUNTIME)
// 自定义约束注解，并指定了用于验证该约束的验证器类
@Constraint(validatedBy = RadioValidator.class)
public @interface Radio {
    String message() default "只能从备选值中选择";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String[] value();
}
