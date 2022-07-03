package org.fintecy.md.bitstamp.serialization;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.fintecy.md.bitstamp.model.Product;

import java.io.IOException;
import java.util.Set;

public class ProductDeserializer extends StdDeserializer<Product> {
    public final static ProductDeserializer INSTANCE = new ProductDeserializer();
    public static final Set<String> REQUIRED_RATE_FIELDS = Set.of("trading", "url_symbol", "name", "minimum_order", "counter_decimals");

    public ProductDeserializer() {
        super(Product.class);
    }

    public static Product parse(JsonParser jp, JsonNode node) {

        for (String field : REQUIRED_RATE_FIELDS) {
            if (!node.has(field)) throw new IllegalStateException("Required field " + field + " is missing");
        }

        return new Product(
                node.get("trading").asText(),
                node.get("base_decimals").asLong(),
                node.get("url_symbol").asText(),
                node.get("name").asText(),
                node.get("instant_and_market_orders").asText(),
                node.get("minimum_order").asText(),
                node.get("counter_decimals").asLong(),
                node.get("description").asText());
    }

    @Override
    public Product deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
        final JsonNode node = jp.getCodec().readTree(jp);
        return parse(jp, node);
    }
}
