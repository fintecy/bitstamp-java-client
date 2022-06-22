package org.fintecy.md.bitstamp;

import org.fintecy.md.bitstamp.model.Currency;
import org.fintecy.md.bitstamp.model.ExchangeRate;
import org.fintecy.md.bitstamp.model.RatesRequest;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

import static org.fintecy.md.bitstamp.model.Currency.currency;

/**
 * Available for testing purposes
 */
public class NoOpBitstampApi implements BitstampApi {
    public static final Set<Currency> SUPPORTED_CURRENCIES = Set.of(
            currency("GBP"), currency("EUR"), currency("USD"),
            currency("BTC"), currency("ETH"), currency("BCH")
    );

    @Override
    public CompletableFuture<ExchangeRate> latest(RatesRequest params) {
        throw new IllegalStateException("not implemented");
    }

    @Override
    public CompletableFuture<Set<Currency>> currencies() {
        return CompletableFuture.completedFuture(SUPPORTED_CURRENCIES);
    }
}
