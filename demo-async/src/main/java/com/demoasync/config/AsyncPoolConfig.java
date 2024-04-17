package com.demoasync.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

@Configuration
public class AsyncPoolConfig {
    @Bean
    public ThreadPoolTaskExecutor asyncThreadPoolTaskExecutor(){
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        // 设置线程池核心线程的数量
        executor.setCorePoolSize(20);
        // 设置线程池维护的线程的最大数量
        executor.setMaxPoolSize(200);
        // 设置缓冲队列的数量
        executor.setQueueCapacity(25);
        // 设置超出核心线程数外的线程在空闲时候的最大存活时间
        executor.setKeepAliveSeconds(200);
        // 设置线程名前缀
        executor.setThreadNamePrefix("asyncThread");
        // 设置是否等待所有线程执行完毕才关闭线程池
        executor.setWaitForTasksToCompleteOnShutdown(true);
        // 设置 waitForTasksToCompleteOnShutdown 的等待的时长
        executor.setAwaitTerminationSeconds(60);
        // 设置当没有线程可以被使用时的处理策略，此处设置 callerRunsPolicy 用于被拒绝任务的处理程序，它直接在 execute 方法的调用线程中运行被拒绝的任务（如果执行程序已关闭，则会丢弃该任务）
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        // 手动触发初始化过程
        executor.initialize();
        return executor;
    }
}
