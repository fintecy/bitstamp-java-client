package org.fintecy.md.bitstamp;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.failsafe.Policy;
import org.fintecy.md.bitstamp.serialization.BitstampModule;

import java.net.http.HttpClient;
import java.util.ArrayList;
import java.util.List;

class BitstampClientBuilder {
    private ObjectMapper mapper = new ObjectMapper().registerModule(new BitstampModule());
    private HttpClient client = HttpClient.newHttpClient();
    private List<Policy<Object>> policies = new ArrayList<>();
    private String rootPath = BitstampApi.ROOT_PATH;

    public BitstampClientBuilder useClient(HttpClient client) {
        this.client = client;
        return this;
    }

    public BitstampClientBuilder mapper(ObjectMapper mapper) {
        this.mapper = mapper.registerModule(new BitstampModule());
        return this;
    }

    public BitstampClientBuilder rootPath(String rootPath) {
        this.rootPath = rootPath;
        return this;
    }

    public BitstampClientBuilder with(Policy<Object> policy) {
        this.policies.add(policy);
        return this;
    }

    public BitstampApi build() {
        return new BitstampClient(rootPath, mapper, client, policies);
    }
}
