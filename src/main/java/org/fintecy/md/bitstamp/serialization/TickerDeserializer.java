package org.fintecy.md.bitstamp.serialization;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.fintecy.md.bitstamp.model.Candle;
import org.fintecy.md.bitstamp.model.Ticker;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Set;

import static java.lang.Long.parseLong;
import static java.time.Instant.ofEpochSecond;

public class TickerDeserializer extends StdDeserializer<Candle> {
    public final static TickerDeserializer INSTANCE = new TickerDeserializer();
    public static final Set<String> REQUIRED_RATE_FIELDS = Set.of("high", "last", "timestamp", "bid", "vwap", "volume", "low", "ask", "open");

    public TickerDeserializer() {
        super(Ticker.class);
    }

    public static Ticker parse(JsonParser jp, JsonNode node) {

        for (String field : REQUIRED_RATE_FIELDS) {
            if (!node.has(field)) throw new IllegalStateException("Required field " + field + " is missing");
        }

        return new Ticker(
                new BigDecimal(node.get("high").asText()),
                new BigDecimal(node.get("last").asText()),
                ofEpochSecond(parseLong(node.get("timestamp").asText())),
                new BigDecimal(node.get("bid").asText()),
                new BigDecimal(node.get("vwap").asText()),
                new BigDecimal(node.get("volume").asText()),
                new BigDecimal(node.get("low").asText()),
                new BigDecimal(node.get("ask").asText()),
                new BigDecimal(node.get("open").asText())
        );
    }

    @Override
    public Ticker deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
        final JsonNode node = jp.getCodec().readTree(jp);
        return parse(jp, node);
    }
}
