package com.demoscheduled.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import java.util.concurrent.Executor;

@Configuration
public class SchedulerConfig {
    @Bean(name = "billThreadPool")
    public Executor billExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        // 设置核心线程数为 3
        executor.setCorePoolSize(3);
        // 最大线程数为10
        executor.setMaxPoolSize(10);
        // 任务队列的大小
        executor.setQueueCapacity(3);
        // 线程前缀名
        executor.setThreadNamePrefix("billExecutor-");
        // 线程存活时间
        executor.setKeepAliveSeconds(30);
        // 初始化
        executor.initialize();
        return executor;
    }

    @Bean(name = "taskScheduler")
    public ThreadPoolTaskScheduler taskScheduler() {
        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        // 配置线程池大小
        scheduler.setPoolSize(10);
        scheduler.setThreadNamePrefix("taskScheduler-");
        scheduler.setWaitForTasksToCompleteOnShutdown(true);
        scheduler.setAwaitTerminationSeconds(30);
        return scheduler;
    }
}
