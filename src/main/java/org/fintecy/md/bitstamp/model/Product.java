package org.fintecy.md.bitstamp.model;

import java.util.Objects;

public class Product {
    private final String trading;
    private final long baseDecimals;
    private final String urlSymbol;
    private final String name;
    private final String instantAndMarketOrders;
    private final String minimumOrder;
    private final long counterDecimals;
    private final String description;

    public Product(String trading, long baseDecimals, String urlSymbol, String name, String instantAndMarketOrders,
                   String minimumOrder, long counterDecimals, String description) {
        this.trading = trading;
        this.baseDecimals = baseDecimals;
        this.urlSymbol = urlSymbol;
        this.name = name;
        this.instantAndMarketOrders = instantAndMarketOrders;
        this.minimumOrder = minimumOrder;
        this.counterDecimals = counterDecimals;
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(trading, product.trading)
                && Objects.equals(baseDecimals, product.baseDecimals)
                && Objects.equals(urlSymbol, product.urlSymbol)
                && Objects.equals(name, product.name)
                && Objects.equals(instantAndMarketOrders, product.instantAndMarketOrders)
                && Objects.equals(minimumOrder, product.minimumOrder)
                && Objects.equals(counterDecimals, product.counterDecimals)
                && Objects.equals(description, product.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(trading, baseDecimals, urlSymbol, name, instantAndMarketOrders, minimumOrder, counterDecimals, description);
    }

    @Override
    public String toString() {
        return "Product{" +
                "trading='" + trading + '\'' +
                ", baseDecimals='" + baseDecimals + '\'' +
                ", urlSymbol='" + urlSymbol + '\'' +
                ", name='" + name + '\'' +
                ", instantAndMarketOrders='" + instantAndMarketOrders + '\'' +
                ", minimumOrder='" + minimumOrder + '\'' +
                ", counterDecimals='" + counterDecimals + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
