package com.alura.literalura.api;

import com.alura.literalura.dto.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ApiRequestTest {

    private ApiRequest apiRequest;

    @BeforeEach
    void initializeApiRequest(){
        this.apiRequest = new ApiRequest();
    }

    @Test
    void testGetBooksHasBody() throws IOException, InterruptedException {
        Book book =  apiRequest.getBookById(87);
        assertNotNull(book);
    }


    @Test
    void searchByTitle() throws IOException, InterruptedException {
        List<Book> books = apiRequest.searchByTitle("Moby");
        System.out.println(books.get(0).title());
     }
}