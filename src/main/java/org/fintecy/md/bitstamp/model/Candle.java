package org.fintecy.md.bitstamp.model;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;

public class Candle {
    private final BigDecimal high;
    private final BigDecimal last;
    private final Instant timestamp;
    private final BigDecimal bid;
    private final BigDecimal vwap;
    private final BigDecimal volume;
    private final BigDecimal low;
    private final BigDecimal ask;
    private final BigDecimal open;

    public Candle(BigDecimal high, BigDecimal last, Instant timestamp, BigDecimal bid, BigDecimal vwap,
                  BigDecimal volume, BigDecimal low, BigDecimal ask, BigDecimal open) {
        this.high = high;
        this.last = last;
        this.timestamp = timestamp;
        this.bid = bid;
        this.vwap = vwap;
        this.volume = volume;
        this.low = low;
        this.ask = ask;
        this.open = open;
    }

    public BigDecimal getHigh() {
        return high;
    }

    public BigDecimal getLast() {
        return last;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public BigDecimal getBid() {
        return bid;
    }

    public BigDecimal getVwap() {
        return vwap;
    }

    public BigDecimal getVolume() {
        return volume;
    }

    public BigDecimal getLow() {
        return low;
    }

    public BigDecimal getAsk() {
        return ask;
    }

    public BigDecimal getOpen() {
        return open;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Candle candle = (Candle) o;
        return Objects.equals(high, candle.high)
                && Objects.equals(last, candle.last)
                && Objects.equals(timestamp, candle.timestamp)
                && Objects.equals(bid, candle.bid)
                && Objects.equals(vwap, candle.vwap)
                && Objects.equals(volume, candle.volume)
                && Objects.equals(low, candle.low)
                && Objects.equals(ask, candle.ask)
                && Objects.equals(open, candle.open);
    }

    @Override
    public int hashCode() {
        return Objects.hash(high, last, timestamp, bid, vwap, volume, low, ask, open);
    }

    @Override
    public String toString() {
        return "Candle{" +
                "high='" + high + '\'' +
                ", last='" + last + '\'' +
                ", timestamp='" + timestamp + '\'' +
                ", bid='" + bid + '\'' +
                ", vwap='" + vwap + '\'' +
                ", volume='" + volume + '\'' +
                ", low='" + low + '\'' +
                ", ask='" + ask + '\'' +
                ", open='" + open + '\'' +
                '}';
    }
}
