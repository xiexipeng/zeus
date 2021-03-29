package com.xxp.util;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.Date;

/**
 * @author: xiexipeng@u51.com
 * @create: 2021/03/17 15:52:17
 * @description: 日期工具测试用例
 * @Version V1.0
 **/
public class DateUtilTest {

    @Test
    public void test_getTimeByTimeMillis(){
        long timeMillis = System.currentTimeMillis();
        LocalDateTime timeByTimeMillis = DateUtil.getTimeByTimeMillis(timeMillis - timeMillis %1000);
        System.out.println(timeByTimeMillis);
    }

    @Test
    public void test_getDateTimeByTimeMillis(){
        long timeMillis = System.currentTimeMillis();
        Date dateTimeByTimeMillis = DateUtil.getDateTimeByTimeMillis(timeMillis);
        System.out.println(dateTimeByTimeMillis);
    }

    @Test
    public void test_getTimeMillisByTime(){
        System.out.println(DateUtil.getTimeMillisByTime(LocalDateTime.now()));
    }

    @Test
    public void test_withLastDayOfMonth(){
        LocalDateTime now = LocalDateTime.now();
        System.out.println(DateUtil.withLastDayOfMonth(now));
        System.out.println(now);
    }

    @Test
    public void test_withTimeByPeriod(){
        System.out.println(DateUtil.withTimeByField(LocalDateTime.now(), ChronoField.DAY_OF_MONTH, 10));
    }

    @Test
    public void test_getDuration(){
        LocalDateTime time1 = LocalDateTime.of(2020, 9, 20, 19, 10, 20);
        LocalDateTime time2 = LocalDateTime.of(2020, 9, 20, 19, 20, 20);
        Duration duration = DateUtil.getDuration(time1,time2);
        System.out.println(duration.getSeconds());
    }

    @Test
    public void test_getInstance(){
        LocalDateTime time1 = LocalDateTime.of(2020, 9, 20, 19, 10, 20);
        LocalDateTime time2 = LocalDateTime.of(2020, 9, 20, 19, 20, 20);
        System.out.println(DateUtil.getInstance(time1,time2, ChronoUnit.DAYS));
        System.out.println(DateUtil.getInstance(time1,time2, ChronoUnit.MINUTES));
    }

    @Test
    public void test_truncate(){
        System.out.println(DateUtil.truncate(LocalDateTime.now(),ChronoUnit.HOURS));
    }

    @Test
    public void test_parseByDefault(){
        System.out.println(DateUtil.parse("2021-03-25T14:50:47.000+08","yyyy-MM-dd'T'HH:mm:ss.SSS+08"));
    }
}
