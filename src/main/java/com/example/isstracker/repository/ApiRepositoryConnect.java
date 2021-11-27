package com.example.isstracker.repository;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Optional;


public class ApiRepositoryConnect<T> {
    private final HttpClient client = HttpClient.newHttpClient();
    private final Class<T> modelClass;
    private final ObjectMapper jsonObjectMapper = new ObjectMapper();

    public ApiRepositoryConnect(Class<T> modelClass) {
        this.modelClass = modelClass;
    }

    private String getResponseBody(URI uri) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(uri)
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() != 200) {
            System.err.println("status code is not ok");
            return "";
        }
        return response.body();
    }

    public Optional<T> get(URI uri) throws IOException, InterruptedException {
        String apiBodyResponse = getResponseBody(uri);
        if (apiBodyResponse.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(jsonObjectMapper.readValue(apiBodyResponse, modelClass));
    }
}
