package org.fintecy.md.bitstamp.serialization;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.fintecy.md.bitstamp.model.OrderBook;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static java.lang.Long.parseLong;
import static java.math.BigDecimal.valueOf;
import static java.time.Instant.ofEpochSecond;

public class OrderBookDeserializer extends StdDeserializer<OrderBook> {
    public final static OrderBookDeserializer INSTANCE = new OrderBookDeserializer();
    public static final Set<String> REQUIRED_RATE_FIELDS = Set.of("timestamp", "bids", "asks");

    public OrderBookDeserializer() {
        super(OrderBook.class);
    }

    public static OrderBook parse(JsonParser jp, JsonNode node) {

        for (String field : REQUIRED_RATE_FIELDS) {
            if (!node.has(field)) throw new IllegalStateException("Required field " + field + " is missing");
        }

        final var bids = getPriceLevels(node, "bids");
        final var asks = getPriceLevels(node, "asks");
        return new OrderBook(ofEpochSecond(parseLong(node.get("timestamp").asText())), bids, asks);
    }

    private static List<BigDecimal> getPriceLevels(JsonNode node, String fieldName) {
        final List<BigDecimal> levels = new ArrayList<>();
        node.get(fieldName).forEach(jsonNode ->
                levels.add(valueOf(jsonNode.asDouble())));
        return levels;
    }

    @Override
    public OrderBook deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
        final JsonNode node = jp.getCodec().readTree(jp);
        return parse(jp, node);
    }
}
