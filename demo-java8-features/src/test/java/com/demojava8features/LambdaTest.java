package com.demojava8features;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.function.BinaryOperator;
import java.util.function.Function;

/**
 * lambda 表达式相关 demo
 * */
@Log4j2
@SpringBootTest
public class LambdaTest {
    @Test
    void lambdaTest() {
        // 能够使用Lambda的依据是必须有相应的函数接口（函数接口，是指内部只有一个抽象方法的接口）。
        // Lambda的类型就是对应函数接口的类型。Lambda表达式另一个依据是类型推断机制，在上下文信息足够的情况下，编译器可以推断出参数表的类型，而不需要显式指名。
        // 无参函数的简写
        Runnable run = () -> log.info("Hello World!");
        // 有参函数的简写，以及类型推断机制
        ActionListener listener = event -> log.info("button clicked");
        // 代码块的写法
        Runnable run2 = () -> {
            String str = "hello world!";
            log.info("{}", str);
        };
        // 类型推断机制
        BinaryOperator<Long> add = (Long x, Long y) -> x + y;
        BinaryOperator<Long> add2 = (x, y) -> x + y;

        // 方法引用
        // 引用静态方法
        Comparator<Integer> sum = Integer::sum;
        // 引用某个对象的方法
        Arrays.asList("1","2").forEach(log::info);
        // 引用某个类的方法
        Function<String, Integer> stringIntegerFunction = String::length;
        // 引用构造方法
        Runnable runnable = HashMap::new;
    }
}
