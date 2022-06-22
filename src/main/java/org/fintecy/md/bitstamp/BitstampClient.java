package org.fintecy.md.bitstamp;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.failsafe.Policy;
import org.fintecy.md.bitstamp.model.CurrenciesResponse;
import org.fintecy.md.bitstamp.model.Currency;
import org.fintecy.md.bitstamp.model.ExchangeRate;
import org.fintecy.md.bitstamp.model.RatesRequest;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

import static java.net.URI.create;
import static java.net.http.HttpResponse.BodyHandlers.ofString;
import static java.util.Optional.ofNullable;

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
    public CompletableFuture<ExchangeRate> latest(RatesRequest request) {
        URI uri = create(rootPath
                + "/exchange/quote"
                + "?country=" + request.getCountry()
                + "&amount=" + request.getAmount()
                + "&fromCurrency=" + request.getFrom().getCode()
                + "&toCurrency=" + request.getTo().getCode()
                + "&isRecipientAmount=" + request.isRecipientAmount()
        );
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(uri)
                .build();

        return client.sendAsync(httpRequest, ofString())
                .thenApply(HttpResponse::body)
                .thenApply(body -> parseResponse(body, ExchangeRate.class));
    }

    @Override
    public CompletableFuture<Set<Currency>> currencies() {
        var httpRequest = HttpRequest.newBuilder()
                .uri(create(rootPath + "/currencies"))
                .build();

        return client.sendAsync(httpRequest, ofString())
                .thenApply(HttpResponse::body)
                .thenApply(body -> parseResponse(body, CurrenciesResponse.class))
                .thenApply(CurrenciesResponse::currencies);
    }

    private <T> T parseResponse(String body, Class<T> tClass) {
        try {
            return mapper.readValue(body, tClass);
        } catch (IOException e) {
            throw new IllegalStateException("Can parse response", e);
        }
    }
}
