package org.fintecy.md.bitstamp.serialization;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.fintecy.md.bitstamp.model.Candle;
import org.fintecy.md.bitstamp.model.OhlcResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.fintecy.md.bitstamp.serialization.CandleDeserializer.parse;

public class OhlcResponseDeserializer extends StdDeserializer<OhlcResponse> {
    public final static OhlcResponseDeserializer INSTANCE = new OhlcResponseDeserializer();

    public OhlcResponseDeserializer() {
        super(OhlcResponse.class);
    }

    @Override
    public OhlcResponse deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
        final JsonNode node = jp.getCodec().readTree(jp);

        List<Candle> candles = new ArrayList<>();
        node.forEach(candleNode ->
                candles.add(parse(jp, candleNode)));
        return new OhlcResponse(candles);
    }
}
