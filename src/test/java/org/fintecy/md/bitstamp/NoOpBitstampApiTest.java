package org.fintecy.md.bitstamp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class NoOpBitstampApiTest {
    private BitstampApi noOpBitstampApi;

    @BeforeEach
    void setUp() {
        noOpBitstampApi = new NoOpBitstampApi();
    }

    @Test
    void should_throw_exception_for_products() {
        assertThrows(IllegalStateException.class, () -> noOpBitstampApi.products().get());
    }
}
