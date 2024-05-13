package com.alura.literalura.assemblers;

import com.alura.literalura.api.ApiRequest;
import com.alura.literalura.dto.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.net.http.HttpResponse;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class BookAssemblerTest {

    private BookAssembler bookAssembler;

    @Autowired
    private ApiRequest apiRequest;

    @BeforeEach
    void initializeAssembler(){
        this.bookAssembler = new BookAssembler();
        this.apiRequest = new ApiRequest();
    }


    @Test
    void fromResponseToDTO() throws IOException, InterruptedException {
        Book book = this.bookAssembler.fromResponseToDTO(this.apiRequest.getBooks(87));
        assertEquals("The 1993 CIA World Factbook", book.title());
        assertEquals("F. Scott Fitzgerald", book.authors());
        assertEquals("English", book.languages());
        assertEquals(1000, book.download_count());
    }
}