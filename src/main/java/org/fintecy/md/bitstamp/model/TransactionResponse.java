package org.fintecy.md.bitstamp.model;

import java.util.List;

public class TransactionResponse extends MicroType<List<Transaction>> {
    public TransactionResponse(List<Transaction> value) {
        super(value);
    }
}
