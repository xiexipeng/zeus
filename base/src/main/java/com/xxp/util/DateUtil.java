package com.xxp.util;

import lombok.experimental.UtilityClass;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.TemporalField;
import java.time.temporal.TemporalUnit;
import java.util.Date;

/**
 * @author: xiexipeng
 * @create: 2021/03/17 15:34:39
 * @description: 日期工具类
 * @Version V1.0
 **/
@UtilityClass
public class DateUtil {

    public LocalDateTime getNow() {
        return LocalDateTime.now();
    }

    public LocalDateTime getTime(int year, int month, int dayOfMonth, int hour, int minute, int second) {
        return LocalDateTime.of(year, month, dayOfMonth, hour, minute, second);
    }

    /**
     * 通过标准格式指定日期和时间
     * <p>
     * 日期：yyyy-MM-dd
     * 时间：HH:mm:ss
     * 带毫秒的时间：HH:mm:ss.SSS
     * 日期和时间：yyyy-MM-dd'T'HH:mm:ss
     * 带毫秒的日期和时间：yyyy-MM-dd'T'HH:mm:ss.SSS
     *
     * @param time
     * @return
     */
    public LocalDateTime parseByDefault(String time) {
        return LocalDateTime.parse(time);
    }

    /**
     * 自定义格式解析
     * 示例formatter: yyyy/MM/dd HH:mm:ss
     * 示例formatter: yyyy-MM-dd'T'HH:mm:ss.SSS+08, 对应时间字符串: 2021-03-25T14:50:47.000+08
     *
     * @param time
     * @param formatter
     * @return
     */
    public LocalDateTime parse(String time, String formatter) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(formatter);
        return LocalDateTime.parse(time, dateTimeFormatter);
    }

    /**
     * 时间戳转换为time
     * @param timeMillis
     * @return
     */
    public LocalDateTime getTimeByTimeMillis(long timeMillis) {
        Instant instantMillis = Instant.ofEpochMilli(timeMillis);
        return instantMillis.atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    /**
     * date转换为time
     * @param date
     * @return
     */
    public LocalDateTime getTimeByDateTime(Date date) {
        if (date == null) {
            return null;
        }
        Instant instant = date.toInstant();
        return LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
    }

    /**
     * 时间戳转换为date
     * @param timeMillis
     * @return
     */
    public Date getDateTimeByTimeMillis(long timeMillis) {
        return Date.from(Instant.ofEpochMilli(timeMillis));
    }

    /**
     * time转换为时间戳
     * @param time
     * @return
     */
    public long getTimeMillisByTime(LocalDateTime time) {
        if (time == null) {
            return 0;
        }
        return time.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    /**
     * date转换为时间戳
     * @param date
     * @return
     */
    public long getTimeMillisByDateTime(Date date) {
        if (date == null) {
            return 0;
        }
        return date.toInstant().toEpochMilli();
    }

    /**
     * time转换为date
     * @param time
     * @return
     */
    public Date getDateTimeByTime(LocalDateTime time) {
        if (time == null) {
            return null;
        }
        return Date.from(time.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 获取本月第一天
     * @param time
     * @return
     */
    public LocalDateTime getFirstDayOfMonth(LocalDateTime time) {
        return time.with(TemporalAdjusters.firstDayOfNextMonth());
    }

    /**
     * 获取本月最后一天日期
     * @param time
     * @return
     */
    public LocalDateTime getLastDayOfMonth(LocalDateTime time) {
        return time.with(TemporalAdjusters.lastDayOfMonth());
    }

    /**
     * 按条件增加日期
     * Period period = Period.ofDays(1);
     *
     * @param time
     * @param period
     * @return
     */
    public LocalDateTime plusByPeriod(LocalDateTime time, Period period) {
        return time.plus(period);
    }

    /**
     * 日期增加
     * @param time
     * @param year
     * @param month
     * @param day
     * @param hour
     * @param minute
     * @param second
     * @return
     */
    public LocalDateTime plus(LocalDateTime time, int year, int month, int day, int hour, int minute, int second) {
        return time.plusYears(year).plusMonths(month).plusDays(day).plusHours(hour).plusMinutes(minute).plusSeconds(second);
    }

    /**
     * 按条件减少日期
     * Period period = Period.ofDays(1);
     *
     * @param time
     * @param period
     * @return
     */
    public LocalDateTime minusByPeriod(LocalDateTime time, Period period) {
        return time.minus(period);
    }

    /**
     * 日期减少
     * @param time
     * @param year
     * @param month
     * @param day
     * @param hour
     * @param minute
     * @param second
     * @return
     */
    public LocalDateTime minus(LocalDateTime time, int year, int month, int day, int hour, int minute, int second) {
        return time.minusYears(year).minusMonths(month).minusDays(day).minusHours(hour).minusMinutes(minute).minusSeconds(second);
    }

    /**
     * 按日期字段获取时间
     * ChronoField.DAY_OF_MONTH, 10
     *
     * @param time
     * @param timeByPeriod
     * @param value
     * @return
     */
    public LocalDateTime withTimeByField(LocalDateTime time, TemporalField timeByPeriod, int value) {
        return time.with(timeByPeriod, value);
    }

    /**
     * 设置时间
     * @param time
     * @param year
     * @param month
     * @param dayOfMonth
     * @param hour
     * @param minute
     * @param second
     * @return
     */
    public LocalDateTime withTime(LocalDateTime time, int year, int month, int dayOfMonth, int hour, int minute, int second) {
        return time.withYear(year).withMonth(month).withDayOfMonth(dayOfMonth).withHour(hour).withMinute(minute).withSecond(second);
    }

    /**
     * 获取日期为本月最后一天
     * @param time
     * @return
     */
    public LocalDateTime withLastDayOfMonth(LocalDateTime time) {
        return time.with(TemporalAdjusters.lastDayOfMonth());
    }

    /**
     * 日期比较，持续时间
     * @param time1
     * @param time2
     * @return Duration获取持续总时长
     */
    public Duration getDuration(LocalDateTime time1, LocalDateTime time2) {
        return Duration.between(time1, time2);
    }

    /**
     * 日期比较
     * @param time1
     * @param time2
     * @param unit 比较单位，日期、时分秒等
     * @return
     */
    public long getInstance(LocalDateTime time1, LocalDateTime time2, TemporalUnit unit) {
        return time1.until(time2, unit);
    }

    /**
     * 截取日期
     * @param time
     * @param unit
     * @return
     */
    public LocalDateTime truncate(LocalDateTime time, TemporalUnit unit) {
        return time.truncatedTo(unit);
    }
}
