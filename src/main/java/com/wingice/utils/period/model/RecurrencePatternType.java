package com.wingice.utils.period.model;

/**
 * The Enum Recurrence Pattern Type.
*/
public enum RecurrencePatternType
{
    /**
    * daily
    */
    DAILY,
    /**
    * weekly
    */
    WEEKLY,
    /**
    * absolute Monthly
    */
    ABSOLUTE_MONTHLY,
    /**
    * relative Monthly
    */
    RELATIVE_MONTHLY,
    /**
    * absolute Yearly
    */
    ABSOLUTE_YEARLY,
    /**
    * relative Yearly
    */
    RELATIVE_YEARLY,
    /**
    * For RecurrencePatternType values that were not expected from the service
    */
    UNEXPECTED_VALUE
}