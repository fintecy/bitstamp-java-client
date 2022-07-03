package org.fintecy.md.bitstamp;

import org.fintecy.md.bitstamp.model.Candle;
import org.fintecy.md.bitstamp.model.Product;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * @author batiaev
 * @see <a href="https://www.bitstamp.net/api">docs</a>
 */
public interface BitstampApi {
    String ROOT_PATH = "https://www.bitstamp.net/api/v2";

    /**
     * @param productId - product id for candlesticks
     * @return Latest candlestick
     * @see <a href="https://www.bitstamp.net/api/v2/ticker/btcusd">test request</a>
     */
    CompletableFuture<Candle> ticker(String productId);

    /**
     * @return list of supported products
     * @see <a href="https://www.bitstamp.net/api/#trading-pairs-info">docs</a>
     * @see <a href="https://www.bitstamp.net/api/v2/trading-pairs-info/">test request</a>
     */
    CompletableFuture<List<Product>> products();
}
