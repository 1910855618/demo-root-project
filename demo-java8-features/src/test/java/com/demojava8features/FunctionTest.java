package com.demojava8features;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

/**
 * 函数式接口相关 demo
 * */
@Log4j2
@SpringBootTest
public class FunctionTest {
    // 自定义函数式接口
    // 上面代码中的@FunctionalInterface是可选的，但加上该标注编译器会帮你检查接口是否符合函数接口规范
    @FunctionalInterface
    public interface ConsumerInterface<T>{
        void accept(T t);
    }

    @Test
    void functionTest() {
        // 函数式接口
        ConsumerInterface<String> consumer = s -> log.info(s);
        // 应为上面的代码实现了 ConsumerInterface 接口的 accept 方法，所以可以直接调用 accept 方法查看效果
        consumer.accept("function interface");

        // Collection 新方法
        List<String> list = new ArrayList<>(Arrays.asList("chicken", "you", "are", "too", "beautiful"));
        // forEach
        // 使用 forEach 结合匿名内部类迭代
        list.forEach(new Consumer<String>(){
            @Override
            public void accept(String s) {
                if(s.length() > 3){
                    log.info(s);
                }
            }
        });
        // 使用类型推断机制
        list.forEach(s -> {
            if(s.length() > 3) {
                log.info(s);
            }
        });
        // removeIf
        // 使用匿名内部类实现，并使用类型推断机制
        list.removeIf(s -> s.length() > 3);
        log.info("removeIf 执行后的 list: {}", list);

        // List 新方法
        // replaceAll
        list = new ArrayList<>(Arrays.asList("chicken", "you", "are", "too", "beautiful"));
        // 使用匿名内部类实现，并使用类型推断机制
        list.replaceAll(s -> {
            if(s.length() > 3) {
                return s.toUpperCase();
            }
            return s;
        });
        log.info("replaceAll 执行后的 list: {}", list);
        // sort
        // 使用匿名内部类实现，并使用类型推断机制
        list.sort((s1, s2) -> s1.length()-s2.length());
        log.info("sort 执行后的 list: {}", list);

        // Map 新方法
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "one");
        map.put(2, "two");
        map.put(3, "three");
        // forEach
        map.forEach((k, y) -> log.info("forEach 查看 map 中的数据：{} - {}", k ,y));
        // getOrDefault
        // 按照给定的 key 查询 Map 中对应的 value ，如果没有找到则返回 defaultValue
        log.info(map.getOrDefault(4, "NoValue"));
        // putIfAbsent
        // 只有在不存在 key 值的映射或映射值为 null 时，才将 value 指定的值放入到 Map 中，否则不对 Map 做更改
        map.putIfAbsent(4, "four");
        log.info("putIfAbsent 会在可以映射值为 null 时，将指定的值放入到 Map 中：{}", map);
        // remove
        // Java8 新增了 remove(Object key, Object value) 方法，只有在当前 Map 中 key 正好映射到 value 时才删除该映射，否则什么也不做
        map.remove(4, "four");
        log.info("remove 删除后 Map 中：{}", map);
        // replace
        // replace(K key, V value)，只有在当前 Map 中 key 的映射存在时才用 value 去替换原来的值，否则什么也不做
        map.replace(3, "oldValue");
        log.info("replace(K key, V value) 替换后 Map 中：{}", map);
        // replace(K key, V oldValue, V newValue)，只有在当前 Map 中 key 的映射存在且等于 oldValue 时才用 newValue 去替换原来的值，否则什么也不做
        map.replace(3, "oldValue", "three");
        log.info("replace(K key, V oldValue, V newValue)替换后 Map 中：{}", map);
        // replaceAll
        map.replaceAll((k, v) -> v.toUpperCase());
        log.info("replaceAll 替换后 Map 中：{}", map);
        // merge
        // 如果Map中key对应的映射不存在或者为null，则将value（不能是null）关联到key上
        // 否则执行remappingFunction，如果执行结果非null则用该结果跟key关联，否则在Map中删除key的映射
        // 一个比较常见的场景是将新的错误信息拼接到原来的信息上，比如：
        map.merge(3, " - new", (v1, v2) -> v1 + v2);
        log.info("merge 合并后 Map 中：{}", map);
        // compute
        // 作用是把remappingFunction的计算结果关联到key上，如果计算结果为null，则在Map中删除key的映射
        map.compute(3, (k, v) -> v == null ? "": v.concat(v));
        log.info("compute 计算后 Map 中：{}", map);
        // computeIfAbsent
        // 只有在当前 Map 中不存在 key 值的映射或映射值为 null 时，才调用 mappingFunction，并在 mappingFunction 执行结果非 null 时，将结果跟 key 关联
        map.computeIfAbsent(4, v -> "four - "+v);
        log.info("computeIfAbsent 计算后 Map 中：{}", map);
        // computeIfPresent
        // 作用跟 computeIfAbsent 相反，即，只有在当前 Map 中存在 key 值的映射且非 null 时，
        // 才调用 remappingFunction，如果 remappingFunction 执行结果为 null，则删除 key 的映射，否则使用该结果替换key原来的映射
        map.computeIfPresent(4, (k, v) -> "key: " + k + ", value: " + v);
        log.info("computeIfPresent 计算后 Map 中：{}", map);
    }
}
