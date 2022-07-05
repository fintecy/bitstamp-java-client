package org.fintecy.md.bitstamp.model;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;

public class Transaction {
    private final Instant date;
    private final long tid;
    private final BigDecimal amount;
    private final TransactionType type;
    private final BigDecimal price;

    public Transaction(Instant date, long tid, BigDecimal amount, TransactionType type, BigDecimal price) {
        this.date = date;
        this.tid = tid;
        this.amount = amount;
        this.type = type;
        this.price = price;
    }

    public Instant getDate() {
        return date;
    }

    public long getTid() {
        return tid;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public TransactionType getType() {
        return type;
    }

    public BigDecimal getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return Objects.equals(date, that.date) && Objects.equals(tid, that.tid) && Objects.equals(amount, that.amount) && Objects.equals(type, that.type) && Objects.equals(price, that.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, tid, amount, type, price);
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "date='" + date + '\'' +
                ", tid='" + tid + '\'' +
                ", amount='" + amount + '\'' +
                ", type='" + type + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}
