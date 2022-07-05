package org.fintecy.md.bitstamp.serialization;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.fintecy.md.bitstamp.model.Transaction;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Set;

import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;
import static java.time.Instant.ofEpochSecond;
import static org.fintecy.md.bitstamp.model.TransactionType.type;

public class TransactionDeserializer extends StdDeserializer<Transaction> {
    public final static TransactionDeserializer INSTANCE = new TransactionDeserializer();
    public static final Set<String> REQUIRED_RATE_FIELDS = Set.of("date", "tid", "amount", "type", "price");

    public TransactionDeserializer() {
        super(Transaction.class);
    }

    public static Transaction parse(JsonParser jp, JsonNode node) {

        for (String field : REQUIRED_RATE_FIELDS) {
            if (!node.has(field)) throw new IllegalStateException("Required field " + field + " is missing");
        }

        return new Transaction(
                ofEpochSecond(parseLong(node.get("date").asText())),
                parseLong(node.get("tid").asText()),
                new BigDecimal(node.get("amount").asText()),
                type(parseInt(node.get("type").asText())),
                new BigDecimal(node.get("price").asText())
        );
    }

    @Override
    public Transaction deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
        final JsonNode node = jp.getCodec().readTree(jp);
        return parse(jp, node);
    }
}
