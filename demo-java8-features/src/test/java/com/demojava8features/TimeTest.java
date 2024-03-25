package com.demojava8features;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.Locale;
import java.util.TimeZone;

/**
 * java8 时间相关 demo
 * */
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
        log.info("atTime 合并 LocalDate 和 LocalTime 转 LocalDateTime：{}", date.atTime(time));
        log.info("atDate 合并 LocalDate 和 LocalTime 转 LocalDateTime：{}", time.atDate(date));
        // 日期时间转换日期
        date = dateTime.toLocalDate();
        // 日期时间转换时间
        time = dateTime.toLocalTime();
        log.info("当前日期时间：{}", dateTime);

        // 第一个参数为秒，第二个参数为纳秒
        Instant instant = Instant.ofEpochSecond(120, 100000);
        log.info("初始化 Instant：{}", instant);
        log.info("当前 Instant：{}", Instant.now());

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
        Duration duration1 = Duration.of(5, ChronoUnit.DAYS); // 五天
        Duration duration2 = Duration.of(1000, ChronoUnit.MILLIS); // 1000 毫秒

        Period period = Period.of(2, 3, 4);
        Period period1 = Period.between(LocalDate.of(1999, 8, 15), LocalDate.now());
        log.info("间隔日期：{}，间隔日期：{}", period, period1.getYears());

        // 修改时间
        LocalDate localDate = LocalDate.of(1999, 8, 15);
        localDate = localDate.withYear(2000); // 修改年份为 2000 年
        localDate = localDate.withMonth(1); // 修改月份为 1 月
        localDate = localDate.withDayOfMonth(1); // 修改日期为 1 号
        log.info("修改后时间：{}-{}-{}", localDate.getYear(), localDate.getMonthValue(), localDate.getDayOfMonth());
        localDate = localDate.plusYears(1); // 加 1 年
        localDate = localDate.plusMonths(1); // 加 1 月
        localDate = localDate.plusDays(1); // 加 1 天
        localDate = localDate.plus(1, ChronoUnit.DAYS); // 加 1 天
        log.info("加后时间：{}-{}-{}", localDate.getYear(), localDate.getMonthValue(), localDate.getDayOfMonth());
        localDate = localDate.minusYears(1); // 减 1 年
        localDate = localDate.minusMonths(1); // 减 1 月
        localDate = localDate.minusDays(1); // 减 1 天
        localDate = localDate.minus(1, ChronoUnit.DAYS); // 减 1 天
        log.info("减后时间：{}-{}-{}", localDate.getYear(), localDate.getMonthValue(), localDate.getDayOfMonth());
        log.info("最近一个周日：{}",
                // 返回距离当前时间最近的一个星期日
                LocalDate.now().with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY)));
        log.info("本月最后一个周六：{}",
                // 返回本月最后一个周六
                LocalDate.now().with(TemporalAdjusters.lastInMonth(DayOfWeek.SATURDAY)));
        // 给定一个日期，计算该日期的下一个工作日（不包括星期六和星期天）
        log.info("TemporalAdjuster 函数式接口自定义计算方式：{}", LocalDate.now().with(temporal -> {
            // 当前日期
            DayOfWeek dayOfWeek1 = DayOfWeek.of(temporal.get(ChronoField.DAY_OF_WEEK));
            // 正常情况只加一天
            int dayToAdd = 1;
            // 周五加三天
            if(dayOfWeek1 == DayOfWeek.FRIDAY) {
                dayToAdd = 3;
            }
            // 周六加两天
            if(dayOfWeek1 == DayOfWeek.SATURDAY) {
                dayToAdd = 2;
            }
            return temporal.plus(dayToAdd, ChronoUnit.DAYS);
        }));

        // 格式化时间
        log.info("BASIC_ISO_DATE 格式化后时间：{}", LocalDateTime.now().format(DateTimeFormatter.BASIC_ISO_DATE));
        log.info("ISO_LOCAL_DATE 格式化后时间：{}", LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE));
        log.info("ISO_LOCAL_TIME 格式化后时间：{}", LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_TIME));
        log.info("自定义1 格式化后时间：{}", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        log.info("自定义2 格式化后时间：{}",
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("今天是：yyyy年 MMMM dd日 E", Locale.CHINESE)));
        // 将字符串转为时间
        log.info("转换后的时间：{}", LocalDate.parse("1999-08-15", DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        log.info("转换后的时间：{}", LocalDateTime.parse("1999-08-15 00:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

        // 时区
        ZoneId shanghaiZoneId = ZoneId.of("Asia/Shanghai");
        ZoneId systemZoneId = ZoneId.systemDefault();
        log.info("上海时区：{}，系统时区：{}", shanghaiZoneId, systemZoneId);
        log.info("所有时区信息：{}", ZoneId.getAvailableZoneIds());
        log.info("老时区转新时区：{}", TimeZone.getDefault().toZoneId());
        log.info("时区时间对象：{}", ZonedDateTime.of(LocalDateTime.now(), ZoneId.systemDefault()));
        log.info("以当前时间和世界标准时间（UTC）/格林威治时间（GMT）的偏差来计算：{}",
                OffsetDateTime.of(LocalDateTime.now(), ZoneOffset.of("+09:00"))); // + 9 小时
    }
}
