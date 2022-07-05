package org.fintecy.md.bitstamp.serialization;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.fintecy.md.bitstamp.model.EurUsdRate;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Set;

public class EurUsdRateDeserializer extends StdDeserializer<EurUsdRate> {
    public final static EurUsdRateDeserializer INSTANCE = new EurUsdRateDeserializer();
    public static final Set<String> REQUIRED_RATE_FIELDS = Set.of("buy", "sell");

    public EurUsdRateDeserializer() {
        super(EurUsdRate.class);
    }

    public static EurUsdRate parse(JsonParser jp, JsonNode node) {

        for (String field : REQUIRED_RATE_FIELDS) {
            if (!node.has(field)) throw new IllegalStateException("Required field " + field + " is missing");
        }

        return new EurUsdRate(
                new BigDecimal(node.get("buy").asText()),
                new BigDecimal(node.get("sell").asText()));
    }

    @Override
    public EurUsdRate deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
        final JsonNode node = jp.getCodec().readTree(jp);
        return parse(jp, node);
    }
}
