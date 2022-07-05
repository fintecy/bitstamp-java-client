package org.fintecy.md.bitstamp.model;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Objects;

public class OrderBook {
    private final Instant timestamp;
    private final List<BigDecimal> bids;
    private final List<BigDecimal> asks;

    public OrderBook(Instant timestamp, List<BigDecimal> bids, List<BigDecimal> asks) {
        this.timestamp = timestamp;
        this.bids = bids;
        this.asks = asks;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public List<BigDecimal> getBids() {
        return bids;
    }

    public List<BigDecimal> getAsks() {
        return asks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderBook orderBook = (OrderBook) o;
        return Objects.equals(timestamp, orderBook.timestamp) && Objects.equals(bids, orderBook.bids) && Objects.equals(asks, orderBook.asks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(timestamp, bids, asks);
    }

    @Override
    public String toString() {
        return "OrderBook{" +
                "timestamp=" + timestamp +
                ", bids=" + bids +
                ", asks=" + asks +
                '}';
    }
}
