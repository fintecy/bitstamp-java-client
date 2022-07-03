package org.fintecy.md.bitstamp.serialization;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.fintecy.md.bitstamp.model.Product;
import org.fintecy.md.bitstamp.model.ProductsResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.fintecy.md.bitstamp.serialization.ProductDeserializer.parse;

public class ProductsResponseDeserializer extends StdDeserializer<ProductsResponse> {
    public final static ProductsResponseDeserializer INSTANCE = new ProductsResponseDeserializer();

    public ProductsResponseDeserializer() {
        super(ProductsResponse.class);
    }

    @Override
    public ProductsResponse deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
        final JsonNode node = jp.getCodec().readTree(jp);

        List<Product> candles = new ArrayList<>();
        node.forEach(candleNode ->
                candles.add(parse(jp, candleNode)));
        return new ProductsResponse(candles);
    }
}
