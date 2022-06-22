package org.fintecy.md.bitstamp.model;

import java.util.Set;

public class CurrenciesResponse extends MicroType<Set<Currency>> {

    public CurrenciesResponse(Set<Currency> currencies) {
        super(currencies);
    }

    public Set<Currency> currencies() {
        return getValue();
    }
}
