package org.fintecy.md.bitstamp;

import org.fintecy.md.bitstamp.model.*;

import java.util.List;
import java.util.Set;
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
     * @param productId - product id for candlesticks
     * @return Latest candlestick
     * @see <a href="https://www.bitstamp.net/api/v2/ticker/btcusd">test request</a>
     */
    CompletableFuture<Candle> hourlyTicker(String productId);

    /**
     * @param productId - product id for order book
     * @return Latest order book
     * @see <a href="https://www.bitstamp.net/api/v2/order_book/btcusd">test request</a>
     */
    CompletableFuture<OrderBook> orderBook(String productId, Grouping grouping);

    default CompletableFuture<OrderBook> orderBook(String productId) {
        return orderBook(productId, Grouping.GROUP_1);
    }

    /**
     * @return list of supported products
     * @see <a href="https://www.bitstamp.net/api/#trading-pairs-info">docs</a>
     * @see <a href="https://www.bitstamp.net/api/v2/trading-pairs-info/">test request</a>
     */
    CompletableFuture<List<Product>> products();

    CompletableFuture<Set<Currency>> currencyPairs();
}
