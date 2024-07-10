package com.demopool2.config;

import com.demopool2.entity.MyObject;
import com.demopool2.factory.MyObjectFactory;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class MyObjectPoolConfig {
    @Bean
    public GenericObjectPool<MyObject> myObjectPool(MyObjectFactory factory) {
        GenericObjectPoolConfig<MyObject> config = new GenericObjectPoolConfig<>();
        // 对象池的最大容量
        config.setMaxTotal(16);
        // 空闲队列是否维持后进先出
        config.setLifo(true);
        // 对象池中最多可以有多少个空闲对象
        config.setMaxIdle(8);
        // 对象池中最少需要有几个空闲对象
        config.setMinIdle(0);
        // 如果多个调用线程在等待获取对象，那么他们之间是否应该先到先得
        config.setFairness(false);
        // 对象池没有可用空闲对象的时候，调用方最长等待多长时间
        config.setMaxWait(Duration.ofSeconds(5));
        // 当往对象池里新增一个对象的时候，是否校验该对象的有效性
        config.setTestOnCreate(false);
        // 当从对象池里借走一个对象的时候，是否校验该对象的有效性
        config.setTestOnBorrow(true);
        // 当往对象池里归还一个对象的时候，是否校验该对象的有效性
        config.setTestOnReturn(false);
        // 当回收器在扫描空闲对象时，是否校验对象的有效性
        config.setTestWhileIdle(true);
        // 回收器线程多久执行一次空闲对象回收
        config.setTimeBetweenEvictionRuns(Duration.ofMinutes(1));
        // 软回收时间阈值
        config.setSoftMinEvictableIdleDuration(Duration.ofMinutes(2));
        // 硬回收时间阈值
        config.setMinEvictableIdleDuration(Duration.ofMinutes(3));
        // 标识回收过程需要检查多少个空闲对象，设置为 -1 意为检查所有空闲对象
        config.setNumTestsPerEvictionRun(-1);
        // 禁用 Java Management Extensions (JMX) 功能，防止：org.springframework.jmx.export.UnableToRegisterMBeanException: Unable to register MBean
        config.setJmxEnabled(false);
        return new GenericObjectPool<>(factory, config);
    }
}
