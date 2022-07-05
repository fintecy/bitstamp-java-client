package org.fintecy.md.bitstamp.model;

public enum TimePeriod {
    MINUTE,
    HOUR,
    DAY;

    public String getValue() {
        return name().toLowerCase();
    }
}
