package org.fintecy.md.bitstamp.model;

public class TransactionType extends MicroType<Long> {
    public TransactionType(long value) {
        super(value);
    }

    public static TransactionType type(long typeId) {
        return new TransactionType(typeId);
    }
}
