package com.wingice.utils.period.model;

import java.io.Serializable;

/**
 * @author 胡昊
 * Description: TODO
 * Date: 2019/8/11
 * Time: 0:04
 * Create: DoubleH
 */
public class PatternedRecurrence implements Serializable {

    /**
     * The Pattern.
     * The frequency of an event.
     */
    public RecurrencePattern pattern;

    /**
     * The Range.
     * The duration of an event.
     */
    public RecurrenceRange range;

    public RecurrencePattern getPattern() {
        return pattern;
    }

    public void setPattern(RecurrencePattern pattern) {
        this.pattern = pattern;
    }

    public RecurrenceRange getRange() {
        return range;
    }

    public void setRange(RecurrenceRange range) {
        this.range = range;
    }
}
