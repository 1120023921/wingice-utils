package com.wingice.utils.datetime;

import org.junit.Test;

import java.time.ZoneId;

/**
 * @author 胡昊
 * Description: 时间工具类
 * Date: 2019/5/19
 * Time: 0:00
 * Create: DoubleH
 */
public class DateTimeUtilsTest {

    @Test
    public void stringToLong() {
        String dateTime = "2019-05-19 15:30:40";
        System.out.println(DateTimeUtils.stringToLong(dateTime, ZoneId.of("AET", ZoneId.SHORT_IDS), "yyyy-MM-dd HH:mm:ss"));
    }

    @Test
    public void stringToLong1() {
        String dateTime = "2019-05-19 15:30:40";
        System.out.println(DateTimeUtils.stringToLong(dateTime));
    }

    @Test
    public void longToString() {
        Long time = 1558251040000L;
        System.out.println(DateTimeUtils.longToString(time, ZoneId.of("CTT", ZoneId.SHORT_IDS), "yyyy-MM-dd HH:mm:ss"));
    }

    @Test
    public void longToString1() {
        Long time = 1558251040000L;
        System.out.println(DateTimeUtils.longToString(time));
    }

    @Test
    public void getStartTimeOfDay() {
        System.out.println(DateTimeUtils.getStartTimeOfDay(ZoneId.of("CTT", ZoneId.SHORT_IDS)));
    }

    @Test
    public void getStartTimeOfDay1() {
        System.out.println(DateTimeUtils.getStartTimeOfDay());
    }
}
