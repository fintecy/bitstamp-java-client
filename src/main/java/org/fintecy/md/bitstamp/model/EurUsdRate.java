package org.fintecy.md.bitstamp.model;

import java.math.BigDecimal;
import java.time.Instant;

import static java.math.BigDecimal.valueOf;
import static java.math.RoundingMode.HALF_UP;
import static java.time.temporal.ChronoUnit.SECONDS;
import static org.fintecy.md.bitstamp.model.Currency.currency;

public class EurUsdRate extends ExchangeRate {
    public EurUsdRate(BigDecimal buy, BigDecimal sell) {
        super(currency("EUR"), currency("USD"), Instant.now().truncatedTo(SECONDS), buy, buy.add(sell).divide(valueOf(2), HALF_UP), sell);
    }
}
