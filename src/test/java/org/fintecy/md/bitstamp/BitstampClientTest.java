package org.fintecy.md.bitstamp;

import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import org.fintecy.md.bitstamp.model.Currency;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutionException;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static java.math.BigDecimal.valueOf;
import static java.time.Instant.ofEpochMilli;
import static org.fintecy.md.bitstamp.BitstampClient.bitstampClient;
import static org.fintecy.md.bitstamp.NoOpBitstampApi.SUPPORTED_CURRENCIES;
import static org.fintecy.md.bitstamp.model.Currency.currency;
import static org.fintecy.md.bitstamp.model.ExchangeRate.exchangeRate;
import static org.junit.jupiter.api.Assertions.assertEquals;

@WireMockTest(httpPort = 7777)
class BitstampClientTest {
    @Test
    void should_return_latest() throws ExecutionException, InterruptedException {
        Currency base = currency("USD");
        Currency counter = currency("GBP");
        stubFor(get("/exchange/quote?country=GB" +
                "&amount=10000" +
                "&fromCurrency=" + base.getCode() +
                "&toCurrency=" + counter.getCode() +
                "&isRecipientAmount=false")
                .willReturn(aResponse()
                        .withBodyFile("currencies.json")));

        var expected = exchangeRate(base, counter, ofEpochMilli(1644537189000L), valueOf(0.73765244));
        var actual = bitstampClient()
                .rootPath("http://localhost:7777")
                .build()
                .latest("USD/GBP")
                .get();
        assertEquals(expected, actual);
    }

    @Test
    void should_return_currencies() throws ExecutionException, InterruptedException {
        var actual = bitstampClient()
                .rootPath("http://localhost:7777")
                .build()
                .currencies()
                .get();
        assertEquals(SUPPORTED_CURRENCIES, actual);
    }
}
