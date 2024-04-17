package com.demoasync.controller;

import com.demoasync.service.AsyncService;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.time.Instant;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Log4j2
@RestController
public class AsyncController {
    @Resource
    private AsyncService asyncService;

    @GetMapping("/testAsync")
    public String testAsync() throws ExecutionException, InterruptedException, TimeoutException {
        long start = Instant.now().toEpochMilli();
        log.info("异步方法开始");
        Future<String> stringFuture = asyncService.asyncMethod();
        String result = stringFuture.get(1, TimeUnit.SECONDS);
        log.info("异步方法返回值：{}", result);
        log.info("异步方法结束");
        long end = Instant.now().toEpochMilli();
        log.info("异步方法总耗时：{} ms", end - start);
        return stringFuture.get();
    }

    @GetMapping("/testSync")
    public String testSync() {
        long start = Instant.now().toEpochMilli();
        log.info("同步方法开始");
        asyncService.syncMethod();
        log.info("同步方法结束");
        long end = Instant.now().toEpochMilli();
        log.info("同步方法总耗时：{} ms", end - start);
        return "success";
    }
}
