package com.demoscheduled.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@EnableAsync
public class DownloadTask {
    @Async("billThreadPool")
    @Scheduled(cron = "*/5 * * * * ?")
    public void billDownloadJobA() throws InterruptedException {
        log.info("开始下载 a 账单文件");
        Thread.sleep(1000);
        log.info("a 账单文件下载完成，进行解密操作");
        Thread.sleep(1000);
        log.info("a 账单文件下载解密完成");
    }

    @Async("billThreadPool")
    @Scheduled(cron = "*/5 * * * * ?")
    public void billDownloadJobB() throws InterruptedException {
        log.info("开始下载 b 账单文件");
        Thread.sleep(1000);
        log.info("b 账单文件下载完成，进行解密操作");
        Thread.sleep(1000);
        log.info("b 账单文件下载解密完成");
    }
}
