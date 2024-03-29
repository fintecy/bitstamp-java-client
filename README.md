[![Build](https://github.com/fintecy/bitstamp-client/actions/workflows/gradle.yml/badge.svg?branch=main)](https://github.com/fintecy/bitstamp-client/actions/workflows/gradle.yml)

# Bitstamp Client

Async client with CompletableFutures based on new HttpClient (java 11+)

## Dependency
https://mvnrepository.com/artifact/org.fintecy.md/bitstamp-client/1.0.0

### Gradle
```
implementation 'org.fintecy.md:bitstamp-client:1.0.0'
```

### Maven
```
<dependency>
<groupId>org.fintecy.md</groupId>
<artifactId>bitstamp-client</artifactId>
<version>1.0.0</version>
</dependency>
```

## Usage
### Simple client creation
```
bitstampApi api = bitstampClient.api();
ExchangeRate latest = api.latest("USD/GBP").get();
```
### Complex client configuration
```
var client = bitstampClient()
    .useClient(HttpClient.newBuilder()
        .followRedirects(HttpClient.Redirect.ALWAYS)
        .priority(10)
        .connectTimeout(Duration.ofMillis(500))
        .executor(Executors.newSingleThreadExecutor())
        .build())
    .with(CircuitBreaker.ofDefaults())
    .with(RateLimiter.smoothBuilder(Duration.ofMillis(100))
        .build())
    .with(RetryPolicy.ofDefaults())
    .with(Timeout.of(Duration.ofMillis(400)))
    .rootPath("https://bitstamp.com/api/") -- just to use stub in tests
    .build();
```

## Dependencies
- Java 11+
- FailSafe
- Slf4j api
- Jackson (databind, datatype-jsr310)
- WireMock (tests)
- Junit5 (tests)

## Author
Anton Batiaev <anton@batiaev.com>
