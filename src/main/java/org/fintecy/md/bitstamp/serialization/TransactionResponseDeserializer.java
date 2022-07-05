package org.fintecy.md.bitstamp.serialization;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.fintecy.md.bitstamp.model.Transaction;
import org.fintecy.md.bitstamp.model.TransactionResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.fintecy.md.bitstamp.serialization.TransactionDeserializer.parse;

public class TransactionResponseDeserializer extends StdDeserializer<TransactionResponse> {
    public final static TransactionResponseDeserializer INSTANCE = new TransactionResponseDeserializer();

    public TransactionResponseDeserializer() {
        super(TransactionResponse.class);
    }

    @Override
    public TransactionResponse deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
        final JsonNode node = jp.getCodec().readTree(jp);

        List<Transaction> tx = new ArrayList<>();
        node.forEach(txNode ->
                tx.add(parse(jp, txNode)));
        return new TransactionResponse(tx);
    }
}
