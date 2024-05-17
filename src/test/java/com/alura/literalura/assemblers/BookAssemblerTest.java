package com.alura.literalura.assemblers;

import com.alura.literalura.api.ApiRequest;
import com.alura.literalura.dto.BookDTO;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

class BookAssemblerTest {

    @Autowired
    private ApiRequest apiRequest;

    @BeforeEach
    void initializeAssembler() {
        this.apiRequest = new ApiRequest();
    }

    @Test
    void fromResponseToDTO() throws IOException, InterruptedException {
        HttpResponse mockResponse = mock(HttpResponse.class);
        String rawBookResponse = """
                {
                            "id":87,
                            "title":"The 1993 CIA World Factbook",
                            "authors":[
                                {"name":"United States. Central Intelligence Agency", "birth_year":null,"death_year":null}
                                ],
                            "translators":[],
                                "subjects":[
                                "Geography -- Handbooks, manuals, etc.",
                                "Political science -- Handbooks, manuals, etc.",
                                "Political statistics -- Handbooks, manuals, etc.",
                                "World politics -- Handbooks, manuals, etc."
                            ],
                            "bookshelves":["CIA World Factbooks"],
                            "languages":["en"],
                            "copyright":false,
                            "media_type":"Text",
                            "formats":{
                                "text/html":"https://www.gutenberg.org/ebooks/87.html.images",
                                "application/epub+zip":"https://www.gutenberg.org/ebooks/87.epub3.images",
                                "application/x-mobipocket-ebook":"https://www.gutenberg.org/ebooks/87.kf8.images",
                                "application/rdf+xml":"https://www.gutenberg.org/ebooks/87.rdf",
                                "image/jpeg":"https://www.gutenberg.org/cache/epub/87/pg87.cover.medium.jpg",
                                "application/octet-stream":"https://www.gutenberg.org/cache/epub/87/pg87-h.zip",
                                "text/plain; charset=us-ascii":"https://www.gutenberg.org/ebooks/87.txt.utf-8"},
                                "download_count":94
                                }""";
        when(mockResponse.body()).thenReturn(rawBookResponse);
        BookDTO book = this.apiRequest.getBookById(87);
        assertEquals("The 1993 CIA World Factbook", book.title());
        assertEquals(List.of("en"), book.languages());
        assertEquals(94, book.download_count());
    }
}
