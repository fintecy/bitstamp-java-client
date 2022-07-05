package org.fintecy.md.bitstamp.model;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;

public class Candle {
    private final String pair;
    private final BigDecimal high;
    private final BigDecimal last;
    private final Instant timestamp;
    private final BigDecimal volume;
    private final BigDecimal low;
    private final BigDecimal open;

    public Candle(String pair, BigDecimal high, BigDecimal last, Instant timestamp,
                  BigDecimal volume, BigDecimal low, BigDecimal open) {
        this.pair = pair;
        this.high = high;
        this.last = last;
        this.timestamp = timestamp;
        this.volume = volume;
        this.low = low;
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

    public String getPair() {
        return pair;
    }

    public BigDecimal getVolume() {
        return volume;
    }

    public BigDecimal getLow() {
        return low;
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
                && Objects.equals(pair, candle.pair)
                && Objects.equals(volume, candle.volume)
                && Objects.equals(low, candle.low)
                && Objects.equals(open, candle.open);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pair, high, last, timestamp, volume, low, open);
    }

    @Override
    public String toString() {
        return "Candle{" +
                "pair='" + pair + '\'' +
                ", high='" + high + '\'' +
                ", last='" + last + '\'' +
                ", timestamp='" + timestamp + '\'' +
                ", volume='" + volume + '\'' +
                ", low='" + low + '\'' +
                ", open='" + open + '\'' +
                '}';
    }
}
