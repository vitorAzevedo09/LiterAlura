package com.alura.literalura.api;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ApiRequest {

    public HttpResponse<String> getBooks(Integer bookId) throws IOException, InterruptedException {
        final String URL = "https://gutendex.com/books/"+bookId;
        HttpClient httpClient = HttpClient.newBuilder().build();
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(URL))
                .GET()
                .build();

        return httpClient.send(httpRequest,HttpResponse.BodyHandlers.ofString());
    }
}
