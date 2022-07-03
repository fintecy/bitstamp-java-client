package org.fintecy.md.bitstamp;

import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import org.fintecy.md.bitstamp.model.Candle;
import org.fintecy.md.bitstamp.model.Product;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static java.lang.Long.parseLong;
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
