package com.wingice.utils.period;

import com.wingice.utils.datetime.DateTimeUtils;
import com.wingice.utils.period.model.*;
import org.junit.Test;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Arrays;


public class PeriodUtilsTest {

    @Test
    public void test(){
        System.out.println(LocalDate.now().atStartOfDay(ZoneOffset.ofHours(8)).toInstant().toEpochMilli());
    }

    @Test
    public void dialyEndDate() {
        PatternedRecurrence patternedRecurrence = new PatternedRecurrence();
        RecurrencePattern recurrencePattern = new RecurrencePattern();
        recurrencePattern.type = RecurrencePatternType.DAILY;
        recurrencePattern.interval = 1;
        RecurrenceRange recurrenceRange = new RecurrenceRange();
        recurrenceRange.startDate = System.currentTimeMillis();
        recurrenceRange.endDate = System.currentTimeMillis() + 7 * 24 * 60 * 60 * 1000;
        recurrenceRange.type = RecurrenceRangeType.END_DATE;
        patternedRecurrence.setPattern(recurrencePattern);
        patternedRecurrence.setRange(recurrenceRange);
        PeriodUtils.dialyEndDate(patternedRecurrence);
    }

    @Test
    public void dialyNumbered() {
        PatternedRecurrence patternedRecurrence = new PatternedRecurrence();
        RecurrencePattern recurrencePattern = new RecurrencePattern();
        recurrencePattern.type = RecurrencePatternType.DAILY;
        recurrencePattern.interval = 1;
        RecurrenceRange recurrenceRange = new RecurrenceRange();
        recurrenceRange.startDate = System.currentTimeMillis();
        recurrenceRange.numberOfOccurrences = 25;
        recurrenceRange.type = RecurrenceRangeType.NUMBERED;
        patternedRecurrence.setPattern(recurrencePattern);
        patternedRecurrence.setRange(recurrenceRange);
        PeriodUtils.dialyNumbered(patternedRecurrence);
    }

    @Test
    public void weeklyEndDate() {
        PatternedRecurrence patternedRecurrence = new PatternedRecurrence();
        RecurrencePattern recurrencePattern = new RecurrencePattern();
        recurrencePattern.type = RecurrencePatternType.WEEKLY;
        recurrencePattern.firstDayOfWeek = DayOfWeek.SUNDAY;
        recurrencePattern.daysOfWeek = Arrays.asList(DayOfWeek.SUNDAY, DayOfWeek.WEDNESDAY);
        recurrencePattern.interval = 2;
        RecurrenceRange recurrenceRange = new RecurrenceRange();
        recurrenceRange.startDate = 1572537600000L;
        recurrenceRange.endDate = 1575043200000L;
        recurrenceRange.type = RecurrenceRangeType.END_DATE;
        patternedRecurrence.setPattern(recurrencePattern);
        patternedRecurrence.setRange(recurrenceRange);
        PeriodUtils.weeklyEndDate(patternedRecurrence);
    }

    @Test
    public void weeklyNumbered() {
        PatternedRecurrence patternedRecurrence = new PatternedRecurrence();
        RecurrencePattern recurrencePattern = new RecurrencePattern();
        recurrencePattern.type = RecurrencePatternType.WEEKLY;
        recurrencePattern.firstDayOfWeek = DayOfWeek.SUNDAY;
        recurrencePattern.daysOfWeek = Arrays.asList(DayOfWeek.FRIDAY, DayOfWeek.SATURDAY);
        recurrencePattern.interval = 2;
        RecurrenceRange recurrenceRange = new RecurrenceRange();
        recurrenceRange.startDate = DateTimeUtils.getStartTimeOfDay();
        recurrenceRange.numberOfOccurrences = 5;
        recurrenceRange.type = RecurrenceRangeType.NUMBERED;
        patternedRecurrence.setPattern(recurrencePattern);
        patternedRecurrence.setRange(recurrenceRange);
        PeriodUtils.weeklyNumbered(patternedRecurrence);
    }

    @Test
    public void absoluteMonthlyEndDate() {
        PatternedRecurrence patternedRecurrence = new PatternedRecurrence();
        RecurrencePattern recurrencePattern = new RecurrencePattern();
        recurrencePattern.type = RecurrencePatternType.ABSOLUTE_MONTHLY;
        recurrencePattern.dayOfMonth = 12;
        recurrencePattern.interval = 1;
        RecurrenceRange recurrenceRange = new RecurrenceRange();
        recurrenceRange.startDate = DateTimeUtils.getStartTimeOfDay();
        recurrenceRange.endDate = recurrenceRange.startDate + (90L * 24 * 60 * 60 * 1000);
        recurrenceRange.type = RecurrenceRangeType.END_DATE;
        patternedRecurrence.setPattern(recurrencePattern);
        patternedRecurrence.setRange(recurrenceRange);
        PeriodUtils.absoluteMonthlyEndDate(patternedRecurrence);
    }

    @Test
    public void absoluteMonthlyNumbered() {
        PatternedRecurrence patternedRecurrence = new PatternedRecurrence();
        RecurrencePattern recurrencePattern = new RecurrencePattern();
        recurrencePattern.type = RecurrencePatternType.ABSOLUTE_MONTHLY;
        recurrencePattern.interval = 2;
        recurrencePattern.dayOfMonth = 12;
        RecurrenceRange recurrenceRange = new RecurrenceRange();
        recurrenceRange.startDate = DateTimeUtils.getStartTimeOfDay();
        recurrenceRange.numberOfOccurrences = 10;
        recurrenceRange.type = RecurrenceRangeType.NUMBERED;
        patternedRecurrence.setPattern(recurrencePattern);
        patternedRecurrence.setRange(recurrenceRange);
        PeriodUtils.absoluteMonthlyNumbered(patternedRecurrence);
    }

    @Test
    public void relativeMonthlyEndDate() {
        PatternedRecurrence patternedRecurrence = new PatternedRecurrence();
        RecurrencePattern recurrencePattern = new RecurrencePattern();
        recurrencePattern.type = RecurrencePatternType.RELATIVE_MONTHLY;
        recurrencePattern.daysOfWeek = Arrays.asList(DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY, DayOfWeek.THURSDAY, DayOfWeek.FRIDAY, DayOfWeek.SATURDAY, DayOfWeek.SUNDAY);
        recurrencePattern.interval = 1;
        recurrencePattern.index = WeekIndex.FOURTH;
        RecurrenceRange recurrenceRange = new RecurrenceRange();
        recurrenceRange.startDate = DateTimeUtils.getStartTimeOfDay();
        recurrenceRange.endDate = recurrenceRange.startDate + (90L * 24 * 60 * 60 * 1000);
        recurrenceRange.type = RecurrenceRangeType.END_DATE;
        patternedRecurrence.setPattern(recurrencePattern);
        patternedRecurrence.setRange(recurrenceRange);
        PeriodUtils.relativeMonthlyEndDate(patternedRecurrence);
    }

    @Test
    public void relativeMonthlyNumbered() {
        PatternedRecurrence patternedRecurrence = new PatternedRecurrence();
        RecurrencePattern recurrencePattern = new RecurrencePattern();
        recurrencePattern.type = RecurrencePatternType.RELATIVE_MONTHLY;
        recurrencePattern.daysOfWeek = Arrays.asList(DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY, DayOfWeek.THURSDAY, DayOfWeek.FRIDAY, DayOfWeek.SATURDAY, DayOfWeek.SUNDAY);
        recurrencePattern.interval = 2;
        recurrencePattern.index = WeekIndex.FIRST;
        RecurrenceRange recurrenceRange = new RecurrenceRange();
        recurrenceRange.startDate = DateTimeUtils.getStartTimeOfDay();
        recurrenceRange.numberOfOccurrences = 5;
        recurrenceRange.type = RecurrenceRangeType.NUMBERED;
        patternedRecurrence.setPattern(recurrencePattern);
        patternedRecurrence.setRange(recurrenceRange);
        PeriodUtils.relativeMonthlyNumbered(patternedRecurrence);
    }

    @Test
    public void absoluteYearlyEndDate() {
        PatternedRecurrence patternedRecurrence = new PatternedRecurrence();
        RecurrencePattern recurrencePattern = new RecurrencePattern();
        recurrencePattern.type = RecurrencePatternType.ABSOLUTE_YEARLY;
        recurrencePattern.month = 8;
        recurrencePattern.dayOfMonth = 31;
        recurrencePattern.interval = 2;
        RecurrenceRange recurrenceRange = new RecurrenceRange();
        recurrenceRange.startDate = System.currentTimeMillis();
        recurrenceRange.endDate = System.currentTimeMillis() + 5 * 365 * 24 * 60 * 60 * 1000L;
        recurrenceRange.type = RecurrenceRangeType.END_DATE;
        patternedRecurrence.setPattern(recurrencePattern);
        patternedRecurrence.setRange(recurrenceRange);
        PeriodUtils.absoluteYearlyEndDate(patternedRecurrence);
    }

    @Test
    public void absoluteYearlyNumbered() {
        PatternedRecurrence patternedRecurrence = new PatternedRecurrence();
        RecurrencePattern recurrencePattern = new RecurrencePattern();
        recurrencePattern.type = RecurrencePatternType.ABSOLUTE_YEARLY;
        recurrencePattern.month = 8;
        recurrencePattern.dayOfMonth = 31;
        recurrencePattern.interval = 2;
        RecurrenceRange recurrenceRange = new RecurrenceRange();
        recurrenceRange.startDate = System.currentTimeMillis();
        recurrenceRange.numberOfOccurrences = 5;
        recurrenceRange.type = RecurrenceRangeType.NUMBERED;
        patternedRecurrence.setPattern(recurrencePattern);
        patternedRecurrence.setRange(recurrenceRange);
        PeriodUtils.absoluteYearlyNumbered(patternedRecurrence);
    }

    @Test
    public void relativeYearlyEndDate() {
        PatternedRecurrence patternedRecurrence = new PatternedRecurrence();
        RecurrencePattern recurrencePattern = new RecurrencePattern();
        recurrencePattern.type = RecurrencePatternType.RELATIVE_MONTHLY;
        recurrencePattern.daysOfWeek = Arrays.asList(DayOfWeek.THURSDAY, DayOfWeek.FRIDAY);
        recurrencePattern.interval = 1;
        recurrencePattern.index = WeekIndex.THIRD;
        recurrencePattern.month = 8;
        RecurrenceRange recurrenceRange = new RecurrenceRange();
        recurrenceRange.startDate = DateTimeUtils.getStartTimeOfDay();
        recurrenceRange.endDate = System.currentTimeMillis() + 5 * 365 * 24 * 60 * 60 * 1000L;
        recurrenceRange.type = RecurrenceRangeType.END_DATE;
        patternedRecurrence.setPattern(recurrencePattern);
        patternedRecurrence.setRange(recurrenceRange);
        PeriodUtils.relativeYearlyEndDate(patternedRecurrence);
    }

    @Test
    public void relativeYearlyNumbered() {
        PatternedRecurrence patternedRecurrence = new PatternedRecurrence();
        RecurrencePattern recurrencePattern = new RecurrencePattern();
        recurrencePattern.type = RecurrencePatternType.RELATIVE_MONTHLY;
        recurrencePattern.daysOfWeek = Arrays.asList(DayOfWeek.THURSDAY, DayOfWeek.FRIDAY);
        recurrencePattern.interval = 1;
        recurrencePattern.month = 8;
        recurrencePattern.index = WeekIndex.THIRD;
        RecurrenceRange recurrenceRange = new RecurrenceRange();
        recurrenceRange.startDate = DateTimeUtils.getStartTimeOfDay();
        recurrenceRange.numberOfOccurrences = 5;
        recurrenceRange.type = RecurrenceRangeType.NUMBERED;
        patternedRecurrence.setPattern(recurrencePattern);
        patternedRecurrence.setRange(recurrenceRange);
        PeriodUtils.relativeYearlyNumbered(patternedRecurrence);
    }
}
