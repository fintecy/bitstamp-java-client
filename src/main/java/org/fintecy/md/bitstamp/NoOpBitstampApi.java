package org.fintecy.md.bitstamp;

import org.fintecy.md.bitstamp.model.Product;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Available for testing purposes
 */
public class NoOpBitstampApi implements BitstampApi {
    @Override
    public CompletableFuture<List<Product>> products() {
        throw new IllegalStateException("not implemented");
    }
}
