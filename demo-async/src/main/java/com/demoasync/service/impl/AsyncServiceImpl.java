package com.demoasync.service.impl;

import com.demoasync.service.AsyncService;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

@Log4j2
@Service
public class AsyncServiceImpl implements AsyncService {
    @Async("asyncThreadPoolTaskExecutor")
    @Override
    public Future<String> asyncMethod() {
        sleep();
        log.info("异步方法内部线程名称：{}", Thread.currentThread().getName());
        return new AsyncResult<>("async execute complete.");
    }

    @Override
    public void syncMethod() {
        sleep();
    }

    private void sleep() {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            log.error(e);
        }
    }
}
