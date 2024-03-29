package org.fintecy.md.bitstamp;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.failsafe.Policy;
import org.fintecy.md.bitstamp.model.*;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

import static java.net.URI.create;
import static java.net.http.HttpResponse.BodyHandlers.ofString;
import static java.util.Optional.ofNullable;
import static org.fintecy.md.bitstamp.NoOpBitstampApi.SUPPORTED_CURRENCIES;

public class BitstampClient implements BitstampApi {
    private final String rootPath;
    private final HttpClient client;
    private final ObjectMapper mapper;
    private final List<Policy<Object>> policies;

    protected BitstampClient(String rootPath, ObjectMapper mapper, HttpClient httpClient, List<Policy<Object>> policies) {
        this.client = checkRequired(httpClient, "Http client required for Bitstamp client");
        this.mapper = checkRequired(mapper, "object mapper is required for serialization");
        this.rootPath = checkRequired(rootPath, "root path cannot be empty");
        this.policies = ofNullable(policies).orElse(List.of());
    }

    public static BitstampApi api() {
        return new BitstampClientBuilder().build();
    }

    public static BitstampClientBuilder bitstampClient() {
        return new BitstampClientBuilder();
    }

    public static double checkRequired(double v, String msg) {
        return (v == 0 ? Optional.<Double>empty() : Optional.of(v))
                .orElseThrow(() -> new IllegalArgumentException(msg));
    }

    public static <T> T checkRequired(T v, String msg) {
        return ofNullable(v)
                .orElseThrow(() -> new IllegalArgumentException(msg));
    }

    @Override
    public CompletableFuture<Ticker> ticker(String productId) {
        var httpRequest = HttpRequest.newBuilder()
                .uri(create(rootPath + "/ticker/" + productId))
                .build();

        return client.sendAsync(httpRequest, ofString())
                .thenApply(HttpResponse::body)
                .thenApply(body -> parseResponse(body, Ticker.class));
    }

    @Override
    public CompletableFuture<Ticker> hourlyTicker(String productId) {
        var httpRequest = HttpRequest.newBuilder()
                .uri(create(rootPath + "/ticker_hour/" + productId))
                .build();

        return client.sendAsync(httpRequest, ofString())
                .thenApply(HttpResponse::body)
                .thenApply(body -> parseResponse(body, Ticker.class));
    }

    @Override
    public CompletableFuture<List<Transaction>> transactions(String productId, TimePeriod timePeriod) {
        var httpRequest = HttpRequest.newBuilder()
                .uri(create(rootPath + "/transactions/" + productId + "?time=" + timePeriod.getValue()))
                .build();

        return client.sendAsync(httpRequest, ofString())
                .thenApply(HttpResponse::body)
                .thenApply(body -> parseResponse(body, TransactionResponse.class))
                .thenApply(MicroType::getValue);
    }

    @Override
    public CompletableFuture<List<Candle>> ohlc(String productId, Instant start, Instant end, CandleStep step, int limit) {
        var httpRequest = HttpRequest.newBuilder()
                .uri(create(rootPath + "/ohlc/" + productId
                        + "?step=" + step.getSeconds()
                        + "&limit=" + limit
                        + (!start.equals(Instant.MIN) ? "&start=" + start.toEpochMilli() : "")
                        + (!end.equals(Instant.MAX) ? "&end=" + end.toEpochMilli() : "")
                ))
                .build();

        return client.sendAsync(httpRequest, ofString())
                .thenApply(HttpResponse::body)
                .thenApply(body -> parseResponse(body, OhlcResponse.class))
                .thenApply(MicroType::getValue);
    }

    @Override
    public CompletableFuture<OrderBook> orderBook(String productId, Grouping grouping) {
        var httpRequest = HttpRequest.newBuilder()
                .uri(create(rootPath + "/order_book/" + productId + "?group=" + grouping.getValue()))
                .build();

        return client.sendAsync(httpRequest, ofString())
                .thenApply(HttpResponse::body)
                .thenApply(body -> parseResponse(body, OrderBook.class));
    }

    @Override
    public CompletableFuture<List<Product>> products() {
        var httpRequest = HttpRequest.newBuilder()
                .uri(create(rootPath + "/trading-pairs-info"))
                .build();

        return client.sendAsync(httpRequest, ofString())
                .thenApply(HttpResponse::body)
                .thenApply(body -> parseResponse(body, ProductsResponse.class))
                .thenApply(ProductsResponse::products);
    }

    @Override
    public CompletableFuture<ExchangeRate> eurusd() {
        var httpRequest = HttpRequest.newBuilder()
                .uri(create(rootPath + "/eur_usd"))
                .build();

        return client.sendAsync(httpRequest, ofString())
                .thenApply(HttpResponse::body)
                .thenApply(body -> parseResponse(body, EurUsdRate.class));
    }

    @Override
    public CompletableFuture<Set<Currency>> currencyPairs() {
        return CompletableFuture.completedFuture(SUPPORTED_CURRENCIES);
    }

    private <T> T parseResponse(String body, Class<T> tClass) {
        try {
            return mapper.readValue(body, tClass);
        } catch (IOException e) {
            throw new IllegalStateException("Can parse response", e);
        }
    }
}
