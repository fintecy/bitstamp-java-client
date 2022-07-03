package org.fintecy.md.bitstamp;

import org.fintecy.md.bitstamp.model.Product;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * @author batiaev
 * @see <a href="https://www.bitstamp.net/api">docs</a>
 */
public interface BitstampApi {
    String ROOT_PATH = "https://www.bitstamp.net/api/v2";
//
//    default CompletableFuture<ExchangeRate> latest(Currency base, Currency counter) {
//        return latest(request().from(base).to(counter).build());
//    }
//
//    default CompletableFuture<ExchangeRate> latest(String pair) {
//        final var split = pair.split("/");
//        if (split.length != 2)
//            throw new IllegalArgumentException("Invalid currency pair");
//        return latest(request().from(currency(split[0])).to(currency(split[1])).build());
//    }
//
//    /**
//     * @param params - request params
//     * @return exchange rate
//     * @see <a href="https://www.bitstamp.net/api/v2/ticker/btcusd">test request</a>
//     */
//    CompletableFuture<ExchangeRate> latest(RatesRequest params);

    /**
     * @return list of supported products
     * @see <a href="https://www.bitstamp.net/api/#trading-pairs-info">docs</a>
     * @see <a href="https://www.bitstamp.net/api/v2/trading-pairs-info/">test request</a>
     */
    CompletableFuture<List<Product>> products();
}
