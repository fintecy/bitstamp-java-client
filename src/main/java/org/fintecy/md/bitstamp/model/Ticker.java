package org.fintecy.md.bitstamp.model;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;

public class Ticker extends Candle {

    private final BigDecimal bid;
    private final BigDecimal vwap;
    private final BigDecimal ask;

    public Ticker(BigDecimal high, BigDecimal last, Instant timestamp, BigDecimal bid, BigDecimal vwap, BigDecimal volume, BigDecimal low, BigDecimal ask, BigDecimal open) {
        super("", high, last, timestamp, volume, low, open);
        this.ask = ask;
        this.bid = bid;
        this.vwap = vwap;
    }

    public BigDecimal getBid() {
        return bid;
    }

    public BigDecimal getVwap() {
        return vwap;
    }

    public BigDecimal getAsk() {
        return ask;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Ticker ticker = (Ticker) o;
        return Objects.equals(bid, ticker.bid) && Objects.equals(vwap, ticker.vwap) && Objects.equals(ask, ticker.ask);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), bid, vwap, ask);
    }

    @Override
    public String toString() {
        return "Ticker{" +
                "bid=" + bid +
                ", vwap=" + vwap +
                ", ask=" + ask +
                "} " + super.toString();
    }
}
