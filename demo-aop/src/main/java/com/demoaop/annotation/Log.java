package com.demoaop.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// 表示这个注解作用于方法
@Target(ElementType.METHOD)
// 表示这个注解保留到运行时
@Retention(RetentionPolicy.RUNTIME)
public @interface Log {
    String value() default "";
}
