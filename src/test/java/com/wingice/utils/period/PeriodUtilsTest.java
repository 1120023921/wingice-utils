package com.wingice.utils.period;

import com.wingice.utils.datetime.DateTimeUtils;
import com.wingice.utils.period.model.*;
import org.junit.Test;

import java.util.Arrays;


public class PeriodUtilsTest {

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
        recurrencePattern.daysOfWeek = Arrays.asList(DayOfWeek.FRIDAY, DayOfWeek.SATURDAY);
        recurrencePattern.interval = 1;
        RecurrenceRange recurrenceRange = new RecurrenceRange();
        recurrenceRange.startDate = DateTimeUtils.getStartTimeOfDay();
        recurrenceRange.endDate = recurrenceRange.startDate + (30L * 24 * 60 * 60 * 1000);
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

}