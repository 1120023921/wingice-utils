package com.wingice.utils.period;

import com.wingice.utils.period.model.*;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

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

    public static List<LocalDate> absoluteMonthlyEndDate(PatternedRecurrence patternedRecurrence) {
        final Calendar calendar = Calendar.getInstance();
        final List<LocalDate> localDateList = new LinkedList<>();
        calendar.setTimeInMillis(patternedRecurrence.getRange().startDate);
        while (true) {
            final long millis = calendar.getTimeInMillis();
            if (millis > patternedRecurrence.getRange().endDate) {
                break;
            } else {
                localDateList.add(Instant.ofEpochMilli(millis).atZone(ZoneId.systemDefault()).toLocalDate());
                calendar.add(Calendar.MONTH, patternedRecurrence.getPattern().interval);
            }
        }
        localDateList.forEach(System.out::println);
        return localDateList;
    }

    public static List<LocalDate> absoluteMonthlyNumbered(PatternedRecurrence patternedRecurrence) {
        final Calendar calendar = Calendar.getInstance();
        final List<LocalDate> localDateList = new LinkedList<>();
        calendar.setTimeInMillis(patternedRecurrence.getRange().startDate);
        for (int i = 0; i < patternedRecurrence.getRange().numberOfOccurrences; i++) {
            final long millis = calendar.getTimeInMillis();
            localDateList.add(Instant.ofEpochMilli(millis).atZone(ZoneId.systemDefault()).toLocalDate());
            calendar.add(Calendar.MONTH, patternedRecurrence.getPattern().interval);
        }
        localDateList.forEach(System.out::println);
        return localDateList;
    }

    public static List<LocalDate> relativeMonthlyEndDate(PatternedRecurrence patternedRecurrence) {
        final Calendar calendar = Calendar.getInstance();
        final List<LocalDate> localDateList = new LinkedList<>();
        calendar.setTimeInMillis(patternedRecurrence.getRange().startDate);
        final LocalDate startDate = Instant.ofEpochMilli(patternedRecurrence.getRange().startDate).atZone(ZoneId.systemDefault()).toLocalDate();
        final LocalDate endDate = Instant.ofEpochMilli(patternedRecurrence.getRange().endDate).atZone(ZoneId.systemDefault()).toLocalDate();
        while (calendar.get(Calendar.MONTH) <= endDate.getMonthValue() - 1) {
            for (DayOfWeek dayOfWeek : patternedRecurrence.getPattern().daysOfWeek) {
                //调整日期至当月第一天
                calendar.add(Calendar.DAY_OF_MONTH, -(calendar.get(Calendar.DAY_OF_MONTH) - 1));
                //当月第一天星期
                int firstWeekOfMonth = Instant.ofEpochMilli(calendar.getTimeInMillis()).atZone(ZoneId.systemDefault()).toLocalDate().getDayOfWeek().getValue();
                //先增加index个周，如果当前月第一周不包含dayOfWeek,index+1
                int nowDayOfWeek = transferFirstDayOfWeek(dayOfWeek) == 1 ? 7 : transferFirstDayOfWeek(dayOfWeek) - 1;
                int index = nowDayOfWeek < firstWeekOfMonth ? transferWeekIndex(patternedRecurrence.getPattern().index) : (transferWeekIndex(patternedRecurrence.getPattern().index) - 1);
                calendar.add(Calendar.WEEK_OF_MONTH, index);
                //增加星期差额
                int offset = nowDayOfWeek - firstWeekOfMonth;
                long dayLong = calendar.getTimeInMillis() + offset * 24 * 60 * 60 * 1000L;
                LocalDate day = Instant.ofEpochMilli(dayLong).atZone(ZoneId.systemDefault()).toLocalDate();
                //如果调整日期后到下个月了，倒退7天取当月最后一个满足条件的,调整后变成上个月了，加7天取第一个满足的
                if (day.getMonthValue() - 1 > calendar.get(Calendar.MONTH)) {
                    day = day.minusWeeks(1);
                } else if (day.getMonthValue() - 1 < calendar.get(Calendar.MONTH)) {
                    day = day.plusWeeks(1);
                }
                //调整最后一星期情况
                if (patternedRecurrence.getPattern().index == WeekIndex.LAST) {
                    day = day.plusWeeks(1).getMonthValue() == day.getMonthValue() ? day.plusWeeks(1) : day;
                }
                if (day.isEqual(startDate) || day.isEqual(endDate) || (day.isAfter(startDate) && day.isBefore(endDate))) {
                    localDateList.add(day);
                }
            }
            calendar.set(Calendar.DAY_OF_MONTH, 1);
            calendar.add(Calendar.MONTH, patternedRecurrence.getPattern().interval);
        }
        localDateList.forEach(System.out::println);
        return localDateList;
    }

    public static List<LocalDate> relativeMonthlyNumbered(PatternedRecurrence patternedRecurrence) {
        final Calendar calendar = Calendar.getInstance();
        final List<LocalDate> localDateList = new LinkedList<>();
        calendar.setTimeInMillis(patternedRecurrence.getRange().startDate);
        final LocalDate startDate = Instant.ofEpochMilli(patternedRecurrence.getRange().startDate).atZone(ZoneId.systemDefault()).toLocalDate();
        for (int i = 0; i < patternedRecurrence.getRange().numberOfOccurrences; i++) {
            for (DayOfWeek dayOfWeek : patternedRecurrence.getPattern().daysOfWeek) {
                //调整日期至当月第一天
                calendar.add(Calendar.DAY_OF_MONTH, -(calendar.get(Calendar.DAY_OF_MONTH) - 1));
                //当月第一天星期
                int firstWeekOfMonth = Instant.ofEpochMilli(calendar.getTimeInMillis()).atZone(ZoneId.systemDefault()).toLocalDate().getDayOfWeek().getValue();
                //先增加index个周，如果当前月第一周不包含dayOfWeek,index+1
                int nowDayOfWeek = transferFirstDayOfWeek(dayOfWeek) == 1 ? 7 : transferFirstDayOfWeek(dayOfWeek) - 1;
                int index = nowDayOfWeek < firstWeekOfMonth ? transferWeekIndex(patternedRecurrence.getPattern().index) : (transferWeekIndex(patternedRecurrence.getPattern().index) - 1);
                calendar.add(Calendar.WEEK_OF_MONTH, index);
                //增加星期差额
                int offset = nowDayOfWeek - firstWeekOfMonth;
                long dayLong = calendar.getTimeInMillis() + offset * 24 * 60 * 60 * 1000L;
                LocalDate day = Instant.ofEpochMilli(dayLong).atZone(ZoneId.systemDefault()).toLocalDate();
                //如果调整日期后到下个月了，倒退7天取当月最后一个满足条件的,调整后变成上个月了，加7天取第一个满足的
                if (day.getMonthValue() - 1 > calendar.get(Calendar.MONTH)) {
                    day = day.minusWeeks(1);
                } else if (day.getMonthValue() - 1 < calendar.get(Calendar.MONTH)) {
                    day = day.plusWeeks(1);
                }
                //调整最后一星期情况
                if (patternedRecurrence.getPattern().index == WeekIndex.LAST) {
                    day = day.plusWeeks(1).getMonthValue() == day.getMonthValue() ? day.plusWeeks(1) : day;
                }
                if (day.isEqual(startDate) || day.isAfter(startDate)) {
                    localDateList.add(day);
                }
            }
            calendar.set(Calendar.DAY_OF_MONTH, 1);
            calendar.add(Calendar.MONTH, patternedRecurrence.getPattern().interval);
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

    private static int transferWeekIndex(WeekIndex weekIndex) {
        switch (weekIndex) {
            case FIRST:
                return 1;
            case SECOND:
                return 2;
            case THIRD:
                return 3;
            case FOURTH:
                return 4;
            default:
                return 4;
        }
    }
}
