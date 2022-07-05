package org.fintecy.md.bitstamp;

import org.fintecy.md.bitstamp.model.*;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toSet;

/**
 * Available for testing purposes
 */
public class NoOpBitstampApi implements BitstampApi {
    public static final Set<Currency> SUPPORTED_CURRENCIES = Stream.of(
            "btc", "usd", "eur", "gbp", "pax", "xrp", "ltc", "eth", "bch", "xlm", "link", "omg", "usdc", "aave", "bat", "uma",
            "dai", "knc", "mkr", "zrx", "gusd", "algo", "audio", "crv", "snx", "uni", "yfi", "comp", "grt", "lrc", "usdt",
            "mana", "matic", "sushi", "chz", "enj", "hbar", "alpha", "axs", "ftt", "sand", "storj", "ada", "fet", "skl",
            "cel", "slp", "sxp", "sgb", "avax", "dydx", "ftm", "shib", "amp", "ens", "gala", "perp", "wbtc", "ctsi", "cvx",
            "imx", "nexo", "ant", "gods", "rad", "band", "inj", "rly", "rndr", "vega", "1inch", "ape", "mpl")
            .map(Currency::currency)
            .collect(toSet());
    @Override
    public CompletableFuture<Candle> ticker(String productId) {
        throw new IllegalStateException("not implemented");
    }

    @Override
    public CompletableFuture<Candle> hourlyTicker(String productId) {
        throw new IllegalStateException("not implemented");
    }

    @Override
    public CompletableFuture<OrderBook> orderBook(String productId, Grouping grouping) {
        throw new IllegalStateException("not implemented");
    }

    @Override
    public CompletableFuture<List<Product>> products() {
        throw new IllegalStateException("not implemented");
    }

    @Override
    public CompletableFuture<Set<Currency>> currencyPairs() {
        return CompletableFuture.completedFuture(SUPPORTED_CURRENCIES);
    }
}
