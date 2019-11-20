package com.wingice.utils.period.model;

import java.io.Serializable;

/**
 * @author 胡昊
 * Description: TODO
 * Date: 2019/8/11
 * Time: 0:02
 * Create: DoubleH
 */
public class RecurrenceRange implements Serializable {
    /**
     * The Type.
     * The recurrence range. The possible values are: endDate, noEnd, numbered. Required.
     */
    public RecurrenceRangeType type;

    /**
     * The Start Date.
     * The date to start applying the recurrence pattern. The first occurrence of the meeting may be this date or later, depending on the recurrence pattern of the event. Must be the same value as the start property of the recurring event. Required.
     */
    public Long startDate;

    /**
     * The End Date.
     * The date to stop applying the recurrence pattern. Depending on the recurrence pattern of the event, the last occurrence of the meeting may not be this date. Required if type is endDate.
     */
    public Long endDate;

    /**
     * The Recurrence Time Zone.
     * Time zone for the startDate and endDate properties. Optional. If not specified, the time zone of the event is used.
     */
    public String recurrenceTimeZone;

    /**
     * The Number Of Occurrences.
     * The number of times to repeat the event. Required and must be positive if type is numbered.
     */
    public Integer numberOfOccurrences;
}
