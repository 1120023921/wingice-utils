package com.wingice.utils.period;

import com.wingice.utils.period.model.*;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

/**
 * @author 胡昊
 * Description: TODO
 * Date: 2019/8/10
 * Time: 23:07
 * Create: DoubleH
 */
public class PeriodUtils {

    /**
     * @param patternedRecurrence 周期信息
     * @return 日期列表
     * @Author 胡昊
     * @Description 有结束日期的按 interval 指定的时间间隔天数重复发生
     * @Date 13:39 2019/8/11
     **/
    public static List<LocalDate> dialyEndDate(PatternedRecurrence patternedRecurrence) {
        final Calendar calendar = Calendar.getInstance();
        final List<LocalDate> localDateList = new LinkedList<>();
        calendar.setTimeInMillis(patternedRecurrence.getRange().startDate);
        while (true) {
            final long millis = calendar.getTimeInMillis();
            if (millis > patternedRecurrence.getRange().endDate) {
                break;
            } else {
                localDateList.add(Instant.ofEpochMilli(millis).atZone(ZoneId.systemDefault()).toLocalDate());
                calendar.add(Calendar.DAY_OF_MONTH, patternedRecurrence.getPattern().interval);
            }
        }
        return localDateList;
    }

    public static List<LocalDate> dialyNumbered(PatternedRecurrence patternedRecurrence) {
        final Calendar calendar = Calendar.getInstance();
        final List<LocalDate> localDateList = new LinkedList<>();
        calendar.setTimeInMillis(patternedRecurrence.getRange().startDate);
        for (int i = 0; i < patternedRecurrence.getRange().numberOfOccurrences; i++) {
            final long millis = calendar.getTimeInMillis();
            localDateList.add(Instant.ofEpochMilli(millis).atZone(ZoneId.systemDefault()).toLocalDate());
            calendar.add(Calendar.DAY_OF_MONTH, patternedRecurrence.getPattern().interval);
        }
        localDateList.forEach(System.out::println);
        return localDateList;
    }

    public static List<LocalDate> weeklyEndDate(PatternedRecurrence patternedRecurrence) {
        final Calendar calendar = Calendar.getInstance();
        final List<LocalDate> localDateList = new LinkedList<>();
        calendar.setTimeInMillis(patternedRecurrence.getRange().startDate);
        calendar.setFirstDayOfWeek(transferFirstDayOfWeek(patternedRecurrence.getPattern().firstDayOfWeek));
        //调整到开始日期当周的第一天
        calendar.add(Calendar.DAY_OF_MONTH, -(calendar.get(Calendar.DAY_OF_WEEK) - 1));
        while (true) {
            int expireNum = 0;
            for (DayOfWeek dayOfWeek : patternedRecurrence.getPattern().daysOfWeek) {
                final long millis = calendar.getTimeInMillis() + (transferFirstDayOfWeek(dayOfWeek) - 1) * 24 * 60 * 60 * 1000L;
                if (millis >= patternedRecurrence.getRange().startDate && millis <= patternedRecurrence.getRange().endDate) {
                    localDateList.add(Instant.ofEpochMilli(millis).atZone(ZoneId.systemDefault()).toLocalDate());
                } else {
                    expireNum++;
                }
            }
            if (expireNum == patternedRecurrence.getPattern().daysOfWeek.size()) {
                break;
            } else {
                calendar.add(Calendar.WEEK_OF_MONTH, patternedRecurrence.getPattern().interval);
            }
        }
        localDateList.forEach(System.out::println);
        return localDateList;
    }

    public static List<LocalDate> weeklyNumbered(PatternedRecurrence patternedRecurrence) {
        final Calendar calendar = Calendar.getInstance();
        final List<LocalDate> localDateList = new LinkedList<>();
        calendar.setTimeInMillis(patternedRecurrence.getRange().startDate);
        calendar.setFirstDayOfWeek(transferFirstDayOfWeek(patternedRecurrence.getPattern().firstDayOfWeek));
        //调整到开始日期当周的第一天
        calendar.add(Calendar.DAY_OF_MONTH, -(calendar.get(Calendar.DAY_OF_WEEK) - 1));
        for (int i = 0; i < patternedRecurrence.getRange().numberOfOccurrences; i++) {
            for (DayOfWeek dayOfWeek : patternedRecurrence.getPattern().daysOfWeek) {
                final long millis = calendar.getTimeInMillis() + (transferFirstDayOfWeek(dayOfWeek) - 1) * 24 * 60 * 60 * 1000L;
                if (millis >= patternedRecurrence.getRange().startDate) {
                    localDateList.add(Instant.ofEpochMilli(millis).atZone(ZoneId.systemDefault()).toLocalDate());
                }
            }
            calendar.add(Calendar.WEEK_OF_MONTH, patternedRecurrence.getPattern().interval);
        }
        localDateList.forEach(System.out::println);
        return localDateList;
    }

    /**
     * @param dayOfWeek 星期
     * @return Calendar星期转换
     * @Author 胡昊
     * @Description //TODO
     * @Date 12:50 2019/8/11
     **/
    private static int transferFirstDayOfWeek(DayOfWeek dayOfWeek) {
        switch (dayOfWeek) {
            case MONDAY:
                return Calendar.MONDAY;
            case TUESDAY:
                return Calendar.TUESDAY;
            case WEDNESDAY:
                return Calendar.WEDNESDAY;
            case THURSDAY:
                return Calendar.THURSDAY;
            case FRIDAY:
                return Calendar.FRIDAY;
            case SATURDAY:
                return Calendar.SATURDAY;
            default:
                return Calendar.SUNDAY;
        }
    }
}
