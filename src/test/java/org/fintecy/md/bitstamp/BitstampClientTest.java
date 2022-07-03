package org.fintecy.md.bitstamp;

import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import org.fintecy.md.bitstamp.model.Product;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.ExecutionException;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.fintecy.md.bitstamp.BitstampClient.bitstampClient;
import static org.junit.jupiter.api.Assertions.assertEquals;

@WireMockTest(httpPort = 7777)
class BitstampClientTest {
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
