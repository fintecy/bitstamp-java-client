package org.fintecy.md.bitstamp.model;

public enum CandleStep {
    MIN(60),
    MIN_3(180),
    MIN5(300),
    MIN15(900),
    MIN30(1800),
    HOUR(3600),
    HOUR2(7200),
    HOUR4(14400),
    HOUR6(21600),
    HOUR12(43200),
    DAY(86400),
    DAY3(259200);

    private final int seconds;

    CandleStep(int seconds) {
        this.seconds = seconds;
    }

    public int getSeconds() {
        return seconds;
    }
}
