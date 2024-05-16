package com.alura.literalura.api;

import com.alura.literalura.assemblers.BookAssembler;
import com.alura.literalura.dto.Book;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class ApiRequest {


    private final BookAssembler bookAssembler;

    public ApiRequest(){
        this.bookAssembler = new BookAssembler();
    }

    public Book getBookById(Integer bookId) throws IOException, InterruptedException {
        final String URL = String.format("https://gutendex.com/books/%d/",bookId);
        HttpClient httpClient = HttpClient.newBuilder().build();
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(URL))
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(httpRequest,HttpResponse.BodyHandlers.ofString());
        return bookAssembler.fromResponseToDTO(response);
    }

    public List<Book> searchByTitle(String title) throws IOException, InterruptedException {
        final String URL = String.format("https://gutendex.com/books/?search=%s",title);
        HttpClient httpClient = HttpClient.newBuilder().build();
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(URL))
                .GET()
                .build();
        HttpResponse<String> response = httpClient.send(httpRequest,HttpResponse.BodyHandlers.ofString());
        return bookAssembler.fromResponseToListDTO(response);
    }
}
