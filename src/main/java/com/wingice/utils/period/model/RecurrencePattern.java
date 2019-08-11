package com.wingice.utils.period.model;

import java.util.List;

/**
 * @author 胡昊
 * Description: TODO
 * Date: 2019/8/10
 * Time: 23:57
 * Create: DoubleH
 */
public class RecurrencePattern {

    public RecurrencePatternType type;

    /**
     * The Interval.
     * The number of units between occurrences, where units can be in days, weeks, months, or years, depending on the type. Required.
     */
    public Integer interval;

    /**
     * The Month.
     * The month in which the event occurs.  This is a number from 1 to 12.
     */
    public Integer month;

    /**
     * The Day Of Month.
     * The day of the month on which the event occurs. Required if type is absoluteMonthly or absoluteYearly.
     */
    public Integer dayOfMonth;

    /**
     * The Days Of Week.
     * A collection of the days of the week on which the event occurs. The possible values are: sunday, monday, tuesday, wednesday, thursday, friday, saturday. If type is relativeMonthly or relativeYearly, and daysOfWeek specifies more than one day, the event falls on the first day that satisfies the pattern.  Required if type is weekly, relativeMonthly, or relativeYearly.
     */
    public List<DayOfWeek> daysOfWeek;

    /**
     * The First Day Of Week.
     * The first day of the week. The possible values are: sunday, monday, tuesday, wednesday, thursday, friday, saturday. Default is sunday. Required if type is weekly.
     */
    public DayOfWeek firstDayOfWeek;

    /**
     * The Index.
     * Specifies on which instance of the allowed days specified in daysOfsWeek the event occurs, counted from the first instance in the month. The possible values are: first, second, third, fourth, last. Default is first. Optional and used if type is relativeMonthly or relativeYearly.
     */
    public WeekIndex index;
}
