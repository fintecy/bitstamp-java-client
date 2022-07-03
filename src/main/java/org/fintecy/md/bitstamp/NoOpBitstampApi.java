package org.fintecy.md.bitstamp;

import org.fintecy.md.bitstamp.model.Candle;
import org.fintecy.md.bitstamp.model.Product;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Available for testing purposes
 */
public class NoOpBitstampApi implements BitstampApi {
    @Override
    public CompletableFuture<Candle> ticker(String productId) {
        throw new IllegalStateException("not implemented");
    }

    @Override
    public CompletableFuture<List<Product>> products() {
        throw new IllegalStateException("not implemented");
    }
}
