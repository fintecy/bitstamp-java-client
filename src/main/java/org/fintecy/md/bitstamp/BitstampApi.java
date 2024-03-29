package org.fintecy.md.bitstamp;

import org.fintecy.md.bitstamp.model.*;

import java.time.Instant;
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
    CompletableFuture<Ticker> ticker(String productId);

    /**
     * @param productId - product id for candlesticks
     * @return Latest candlestick
     * @see <a href="https://www.bitstamp.net/api/v2/ticker_hour/btcusd">test request</a>
     */
    CompletableFuture<Ticker> hourlyTicker(String productId);

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
     * @param productId - product id for order book
     * @return Latest order book
     * @see <a href="https://www.bitstamp.net/api/v2/transactions/btcusd">test request</a>
     */
    CompletableFuture<List<Transaction>> transactions(String productId, TimePeriod timePeriod);

    default CompletableFuture<List<Transaction>> transactions(String productId) {
        return transactions(productId, TimePeriod.HOUR);
    }

    /**
     * @param productId - product id for order book
     * @return Latest order book
     * @see <a href="https://www.bitstamp.net/api/v2/ohlc/btcusd">test request</a>
     */
    CompletableFuture<List<Candle>> ohlc(String productId, Instant start, Instant end, CandleStep step, int limit);

    default CompletableFuture<List<Candle>> ohlc(String productId, CandleStep step, int limit) {
        return ohlc(productId, Instant.MIN, Instant.MAX, step, limit);
    }

    /**
     * @return list of supported products
     * @see <a href="https://www.bitstamp.net/api/#trading-pairs-info">docs</a>
     * @see <a href="https://www.bitstamp.net/api/v2/trading-pairs-info/">test request</a>
     */
    CompletableFuture<List<Product>> products();

    /**
     * @return list of supported products
     * @see <a href="https://www.bitstamp.net/api/#conversion-rate">docs</a>
     * @see <a href="https://www.bitstamp.net/api/v2/eur_usd/">test request</a>
     */
    CompletableFuture<ExchangeRate> eurusd();

    CompletableFuture<Set<Currency>> currencyPairs();
}
