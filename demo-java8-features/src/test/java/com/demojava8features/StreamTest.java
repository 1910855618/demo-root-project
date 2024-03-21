package com.demojava8features;

import lombok.Builder;
import lombok.Data;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Stream 相关 demo
 * */
@Log4j2
@SpringBootTest
public class StreamTest {
    @Test
    void streamTest() {
        // filter
        // 作用是返回一个只包含满足 predicate 条件元素的 Stream
        Stream<String> stream = Stream.of("I", "very", "like", "you");
        stream.filter(str -> str.length()==3).forEach(s -> log.info("filter 过滤：{}", s));
        // distinct
        // 作用是返回一个去除重复元素之后的 Stream
        stream = Stream.of("I", "very", "like", "you", "you");
        stream.distinct().forEach(s -> log.info("distinct 去重：{}", s));
        // sorted
        // 排序函数有两个，一个是用自然顺序排序，一个是使用自定义比较器排序
        stream = Stream.of("I", "very", "like", "you");
        stream.sorted((s1, s2) -> s1.length() - s2.length()).forEach(s -> log.info("sorted 排序：{}", s));
        // map
        // 作用是返回一个对当前所有元素执行执行 mapper 之后的结果组成的 Stream
        stream = Stream.of("I", "very", "like", "you");
        stream.map(s -> s.toUpperCase()).forEach(s -> log.info("map 处理后的数据：{}", s));
        // flatMap
        // 作用是对每个元素执行 mapper 指定的操作，并用所有 mapper 返回的 Stream 中的元素组成一个新的 Stream 作为最终返回结果
        Stream<List<Integer>> stream2 = Stream.of(Arrays.asList(1,2), Arrays.asList(3, 4, 5));
        stream2.flatMap(list -> list.stream()).forEach(v -> log.info("flatMap 平摊后的数据：{}", v));
        // reduce
        // reduce 操作可以实现从一组元素中生成一个值
        // 找出最长的单词
        stream = Stream.of("I", "very", "like", "you");
        Optional<String> longest = stream.reduce((s1, s2) -> s1.length() >= s2.length() ? s1 : s2);
        log.info("reduce 查找出最长的字符串：{}", longest.get());
        // 求单词长度之和
        stream = Stream.of("I", "very", "like", "you");
        Integer lengthSum = stream.reduce(0, // 初始值
                (sum, str) -> sum + str.length(), // 累加器
                (a, b) -> a + b); // 部分和拼接器，并行执行时才会用到
        log.info("reduce 求出的字符串长度之和：{}", lengthSum);
        // collect
        // 将 Stream 规约成 List
        stream = Stream.of("I", "very", "like", "you");
        List<String> list = stream.collect(ArrayList::new, // 定义目标容器
                ArrayList::add, // 元素如何添加导容器中
                ArrayList::addAll); // 多个部分结果如何合并
        list.forEach(s -> log.info("collect 转换后的 list：{}", s));
        // 将 Stream 转换成 list 容器
        stream = Stream.of("I", "very", "like", "you");
        list = stream.collect(Collectors.toList());
        list.forEach(s -> log.info("collect 转换后的 list：{}", s));
        // 将 Stream 转换成 Set
        stream = Stream.of("I", "very", "like", "you");
        Set<String> set = stream.collect(Collectors.toSet());
        set.forEach(s -> log.info("collect 转换后的 set：{}", s));
        // 使用 collect 生成 Map
        List<Student> students =
                Arrays.asList(Student.builder().name("蔡徐坤").school("牛子").grade(1).chinese(15.4F).math(16F).english(20F).build(),
                        Student.builder().name("爱坤").school("清北").grade(3).chinese(45F).math(36F).english(70F).build(),
                        Student.builder().name("小黑子").school("清北").grade(3).chinese(150F).math(150F).english(150F).build());
        // toMap 统计学生平均成绩
        students.stream().collect(Collectors.toMap(Function.identity(), Student::average))
                .forEach((k, v) -> log.info("平均成绩：key: {}, value: {}", k, v));
        // partitioningBy 分出低年纪和高年级
        students.stream().collect(Collectors.partitioningBy(s -> s.getGrade() >= 3))
                .forEach((k, v) -> log.info("高低年纪：key: {}, value: {}", k, v));
        // groupingBy 按学校分组
        students.stream().collect(Collectors.groupingBy(Student::getSchool))
                .forEach((k, v) -> log.info("按校分组：key: {}, value: {}", k, v));
        // groupingBy 下游收集器统计每个学校人数
        students.stream().collect(Collectors.groupingBy(Student::getSchool,
                        Collectors.counting())) // 下游收集器
                .forEach((k, v) -> log.info("学校人数：key: {}, value: {}", k, v));
        // groupingBy 利用下游收集器获取每个学校的学生姓名
        students.stream().collect(Collectors.groupingBy(Student::getSchool,
                Collectors.mapping(Student::getName, // 下游收集器
                        Collectors.toList()))) // 更下游收集器
                .forEach((k, v) -> log.info("学校学生：key: {}, value: {}", k, v));
        // joining 拼接字符串
        stream = Stream.of("I", "very", "like", "you");
        log.info("拼接后的字符串：{}", stream.collect(Collectors.joining()));
        stream = Stream.of("I", "very", "like", "you");
        log.info("拼接后的字符串：{}", stream.collect(Collectors.joining(" ")));
        stream = Stream.of("I", "very", "like", "you");
        log.info("拼接后的字符串：{}", stream.collect(Collectors.joining(" ", "\"", "\"")));

        // parallelStream 并行流
        IntStream intStream = IntStream.range(0, 10);
        intStream.parallel().forEach(i -> {
            Thread thread = Thread.currentThread();
            log.warn("循环数：{}, 线程名：{}", i, thread.getName());
        });
        // 需要注意 parallelStream 是线程不安全的
        List<Integer> values = new ArrayList<>();
        IntStream.range(1, 10000).parallel().forEach(values::add);
        log.info(values.size());
        // 加锁、使用线程安全的集合或者采用 collect 或者 reduce 操作就是满足线程安全的了
        values = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            values.add(i);
        }
        List<Integer> collect = values.stream().parallel().collect(Collectors.toList());
        log.info(collect.size());
    }
}

@Data
@Builder
class Student {
    private String name, school;
    private Integer grade;
    private Float chinese, math, english;

    public Float average() {
        return (chinese + math + english) / 3;
    }
}