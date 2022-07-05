package org.fintecy.md.bitstamp.model;

import java.util.List;

public class OhlcResponse extends MicroType<List<Candle>> {
    public OhlcResponse(List<Candle> candles) {
        super(candles);
    }
}
