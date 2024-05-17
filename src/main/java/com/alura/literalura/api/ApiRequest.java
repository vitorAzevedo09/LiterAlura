package com.alura.literalura.api;

import com.alura.literalura.assemblers.BookAssembler;
import com.alura.literalura.dto.BookDTO;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class ApiRequest {

    private final BookAssembler bookAssembler;

    public ApiRequest() {
        this.bookAssembler = new BookAssembler();
    }

    public BookDTO getBookById(Integer bookId) throws IOException, InterruptedException {
        final String URL = String.format("https://gutendex.com/books/%d/", bookId);
        HttpClient httpClient = HttpClient.newBuilder().build();
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(URL))
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        return bookAssembler.fromResponseToDTO(response);
    }

    public List<BookDTO> searchByTitle(String title) throws IOException, InterruptedException {
        final String URL = "https://gutendex.com/books/?search=" + title.replace(" ", "%20");
        HttpClient httpClient = HttpClient.newBuilder().build();
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(URL))
                .GET()
                .build();
        HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        return bookAssembler.fromResponseToListDTO(response);
    }
}
