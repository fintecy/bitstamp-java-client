package org.fintecy.md.bitstamp.serialization;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.deser.InstantDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.key.InstantKeyDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.key.LocalDateKeyDeserializer;
import org.fintecy.md.bitstamp.model.*;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Map;

public class BitstampModule extends SimpleModule {
    public static final String GROUP_ID = "org.fintecy.md";
    public static final String ARTIFACT_ID = "bitstamp-client";
    public static final Version VERSION = new Version(1, 0, 2, "SNAPSHOT",
            GROUP_ID, ARTIFACT_ID);

    public BitstampModule() {
        super(BitstampModule.class.getSimpleName(), VERSION,
                Map.of(
                        Candle.class, CandleDeserializer.INSTANCE,
                        Ticker.class, TickerDeserializer.INSTANCE,
                        Instant.class, InstantDeserializer.INSTANT,
                        LocalDate.class, LocalDateDeserializer.INSTANCE)
        );

        addDeserializer(ProductsResponse.class, ProductsResponseDeserializer.INSTANCE);
        addDeserializer(TransactionResponse.class, TransactionResponseDeserializer.INSTANCE);
        addDeserializer(OhlcResponse.class, OhlcResponseDeserializer.INSTANCE);
        addDeserializer(Product.class, ProductDeserializer.INSTANCE);
        addDeserializer(EurUsdRate.class, EurUsdRateDeserializer.INSTANCE);
        addDeserializer(Transaction.class, TransactionDeserializer.INSTANCE);
        addDeserializer(OrderBook.class, OrderBookDeserializer.INSTANCE);
        addKeyDeserializer(LocalDate.class, LocalDateKeyDeserializer.INSTANCE);
        addKeyDeserializer(Instant.class, InstantKeyDeserializer.INSTANCE);
    }
}
