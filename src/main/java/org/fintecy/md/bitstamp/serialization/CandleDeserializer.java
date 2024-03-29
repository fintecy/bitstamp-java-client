package org.fintecy.md.bitstamp.serialization;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.fintecy.md.bitstamp.model.Candle;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Set;

import static java.lang.Long.parseLong;
import static java.time.Instant.ofEpochSecond;

public class CandleDeserializer extends StdDeserializer<Candle> {
    public final static CandleDeserializer INSTANCE = new CandleDeserializer();
    public static final Set<String> REQUIRED_RATE_FIELDS = Set.of("high", "close", "timestamp", "volume", "low", "open");

    public CandleDeserializer() {
        super(Candle.class);
    }

    public static Candle parse(JsonParser jp, JsonNode node) {

        for (String field : REQUIRED_RATE_FIELDS) {
            if (!node.has(field)) throw new IllegalStateException("Required field " + field + " is missing");
        }

        return new Candle(
                node.has("pair") ? node.get("pair").asText() : "",
                new BigDecimal(node.get("high").asText()),
                new BigDecimal(node.get("close").asText()),
                ofEpochSecond(parseLong(node.get("timestamp").asText())),
                new BigDecimal(node.get("volume").asText()),
                new BigDecimal(node.get("low").asText()),
                new BigDecimal(node.get("open").asText())
        );
    }

    @Override
    public Candle deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
        final JsonNode node = jp.getCodec().readTree(jp);
        return parse(jp, node);
    }
}
