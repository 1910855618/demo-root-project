package com.demojava8features;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.*;
import java.time.temporal.ChronoUnit;

@Log4j2
@SpringBootTest
public class TimeTest {
    @Test
    void timeTest() {
        // 初始化一个日期
        LocalDate date = LocalDate.of(2024, 3, 22);
        int year = date.getYear(); // 年份
        Month month = date.getMonth(); // 月份
        int dayOfMonth = date.getDayOfMonth(); // 月份中的第几天
        DayOfWeek dayOfWeek = date.getDayOfWeek(); // 一周的第几天
        int length = date.lengthOfMonth(); // 月份天数
        boolean leapYear = date.isLeapYear(); // 是否是闰年
        log.info("初始化的日期年份：{}，月份：{}，月份中的第几天：{}，一周的第几天：{}，月份的天数：{}，是否是闰年：{}",
                year, month, dayOfMonth, dayOfWeek, length, leapYear);
        // 调用静态方法 now 获取当前日期
        log.info("当前日期：{}", LocalDate.now());
        // 初始化一个时间
        LocalTime time = LocalTime.of(16, 13, 17);
        int hour = time.getHour(); // 时
        int minute = time.getMinute(); // 分
        int second = time.getSecond(); // 秒
        log.info("初始化的时：{}，分：{}，秒：{}", hour, minute, second);
        // 调用静态方法 now 获取当前时间
        log.info("当前时间：{}", LocalTime.now());
        // 初始化一个日期时间
        LocalDateTime dateTime = LocalDateTime.of(2024, Month.MARCH, 22, 16, 13, 17);
        // 日期时间转换日期
        date = dateTime.toLocalDate();
        // 日期时间转换时间
        time = dateTime.toLocalTime();
        log.info("当前日期时间：{}", dateTime);
        Instant instant = Instant.ofEpochSecond(120, 100000);
        log.info("初始化纳秒：{}", instant);
        log.info("当前纳秒：{}", Instant.now());
        LocalDateTime fromDateTime = LocalDateTime.of(1999, 8, 15, 0, 0, 0);
        LocalDateTime toDateTime = LocalDateTime.now();
        Duration duration = Duration.between(fromDateTime, toDateTime);
        long days = duration.toDays(); // 这段时间的总天数
        long hours = duration.toHours(); // 这段时间的小时数
        long minutes = duration.toMinutes(); // 这段时间的分钟数
        long seconds = duration.getSeconds(); // 这段时间的秒数
        long milliSeconds = duration.toMillis(); // 这段时间的毫秒数
        long nanoSeconds = duration.toNanos(); // 这段时间的纳秒数
        log.info("间隔时间：{}，相隔了多少天：{}，相隔了多少小时：{}，相隔了多少分钟：{}，相隔了多少秒：{}，相隔了多少毫秒：{}，相隔了多少纳秒：{}",
                duration, days, hours, minutes, seconds, milliSeconds, nanoSeconds);
        Duration duration1 = Duration.of(5, ChronoUnit.DAYS);
        Duration duration2 = Duration.of(1000, ChronoUnit.MILLIS);
        Period period = Period.of(2, 3, 4);
        Period period1 = Period.between(LocalDate.of(1999, 8, 15),
                LocalDate.now());
        log.info("间隔日期：{}，间隔日期：{}", period, period1);
        // ?
    }
}
