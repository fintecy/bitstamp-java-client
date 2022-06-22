package org.fintecy.md.bitstamp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutionException;

import static org.fintecy.md.bitstamp.NoOpBitstampApi.SUPPORTED_CURRENCIES;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class NoOpBitstampApiTest {
    private BitstampApi noOpBitstampApi;

    @BeforeEach
    void setUp() {
        noOpBitstampApi = new NoOpBitstampApi();
    }

    @Test
    void should_throw_exception_for_latest() {
        assertThrows(IllegalStateException.class, () -> noOpBitstampApi.latest("USD/GBP").get());
    }

    @Test
    void should_throw_exception_for_currencies() throws ExecutionException, InterruptedException {
        var currencies = noOpBitstampApi.currencies().get();
        assertEquals(currencies, SUPPORTED_CURRENCIES);
    }
}
