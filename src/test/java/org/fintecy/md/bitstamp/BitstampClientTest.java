package org.fintecy.md.bitstamp;

import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import org.fintecy.md.bitstamp.model.*;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static java.lang.Long.parseLong;
import static java.math.BigDecimal.valueOf;
import static java.time.Instant.ofEpochSecond;
import static org.fintecy.md.bitstamp.BitstampClient.bitstampClient;
import static org.junit.jupiter.api.Assertions.assertEquals;

@WireMockTest(httpPort = 7777)
class BitstampClientTest {
    @Test
    void should_return_candles() throws ExecutionException, InterruptedException {
        String productId = "btcusd";
        stubFor(get("/ticker/" + productId)
                .willReturn(aResponse()
                        .withBodyFile("ticker.json")));

        var expected = new Candle(
                new BigDecimal("19381.29"),
                new BigDecimal("19191.99"),
                ofEpochSecond(parseLong("1656874337")),
                new BigDecimal("19181.40"),
                new BigDecimal("19164.68"),
                new BigDecimal("1646.67158308"),
                new BigDecimal("18763.96"),
                new BigDecimal("19194.60"),
                new BigDecimal("19232.76")
        );
        var actual = bitstampClient()
                .rootPath("http://localhost:7777")
                .build()
                .ticker(productId)
                .get();
        assertEquals(expected, actual);
    }

    @Test
    void should_return_hourly_candles() throws ExecutionException, InterruptedException {
        String productId = "btcusd";
        stubFor(get("/ticker_hour/" + productId)
                .willReturn(aResponse()
                        .withBodyFile("ticker.json")));

        var expected = new Candle(
                new BigDecimal("19381.29"),
                new BigDecimal("19191.99"),
                ofEpochSecond(parseLong("1656874337")),
                new BigDecimal("19181.40"),
                new BigDecimal("19164.68"),
                new BigDecimal("1646.67158308"),
                new BigDecimal("18763.96"),
                new BigDecimal("19194.60"),
                new BigDecimal("19232.76")
        );
        var actual = bitstampClient()
                .rootPath("http://localhost:7777")
                .build()
                .hourlyTicker(productId)
                .get();
        assertEquals(expected, actual);
    }

    @Test
    void should_return_order_book() throws ExecutionException, InterruptedException {
        String productId = "btcusd";
        stubFor(get("/order_book/" + productId + "?group=1")
                .willReturn(aResponse()
                        .withBodyFile("orderBook.json")));

        var expected = new OrderBook(
                ofEpochSecond(parseLong("1656874337")),
                List.of(valueOf(19181.40), valueOf(19180.23)),
                List.of(valueOf(19191.99), valueOf(19381.29)));
        var actual = bitstampClient()
                .rootPath("http://localhost:7777")
                .build()
                .orderBook(productId)
                .get();
        assertEquals(expected, actual);
    }

    @Test
    void should_return_transactions() throws ExecutionException, InterruptedException {
        String productId = "btcusd";
        stubFor(get("/transactions/" + productId + "?time=hour")
                .willReturn(aResponse()
                        .withBodyFile("transactions.json")));

        var expected = List.of(
                new Transaction(
                        ofEpochSecond(parseLong("1656961327")),
                        239874427L,
                        new BigDecimal("0.00736000"),
                        TransactionType.type(0),
                        new BigDecimal("19908.34")),
                new Transaction(
                        ofEpochSecond(parseLong("1656961297")),
                        239874401L,
                        new BigDecimal("0.00895000"),
                        TransactionType.type(0),
                        new BigDecimal("19908.52"))
        );
        var actual = bitstampClient()
                .rootPath("http://localhost:7777")
                .build()
                .transactions(productId)
                .get();
        assertEquals(expected, actual);
    }

    @Test
    void should_return_products() throws ExecutionException, InterruptedException {
        stubFor(get("/trading-pairs-info")
                .willReturn(aResponse()
                        .withBodyFile("products.json")));

        var expected = List.of(
                new Product("Enabled", 8, "btcusd", "BTC/USD",
                        "Enabled", "10.0 USD", 2, "Bitcoin / U.S. dollar"),
                new Product("Enabled", 8, "ethbtc", "ETH/BTC",
                        "Enabled", "0.0002 BTC", 8, "Ether / Bitcoin")
        );
        var actual = bitstampClient()
                .rootPath("http://localhost:7777")
                .build()
                .products()
                .get();
        assertEquals(expected, actual);
    }
}
