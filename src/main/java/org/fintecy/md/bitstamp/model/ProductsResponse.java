package org.fintecy.md.bitstamp.model;

import java.util.List;

public class ProductsResponse extends MicroType<List<Product>> {
    public ProductsResponse(List<Product> value) {
        super(value);
    }

    public List<Product> products() {
        return getValue();
    }
}
