package com.alura.literalura.assemblers;

import com.alura.literalura.dto.BookDTO;
import com.alura.literalura.dto.ResponseFromSearch;
import com.alura.literalura.entities.Author;
import com.alura.literalura.entities.Book;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.http.HttpResponse;
import java.util.List;

public class BookAssembler {

    public BookDTO fromResponseToDTO(HttpResponse<String> response) {
        ObjectMapper objectMapper = new ObjectMapper();
        String body = response.body();
        try {
            return objectMapper.readValue(body, BookDTO.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public List<BookDTO> fromResponseToListDTO(HttpResponse<String> response) {
        ObjectMapper objectMapper = new ObjectMapper();
        String body = response.body();
        try {
            ResponseFromSearch responseDTO = objectMapper.readValue(body, ResponseFromSearch.class);
            return responseDTO.results();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public Book fromDTOToEntity(BookDTO bookDTO) {
        Book book = new Book();
        Author author = new Author(
                bookDTO.authors().get(0).name(),
                bookDTO.authors().get(0).birth_year(),
                bookDTO.authors().get(0).death_year());
        book.setTitle(bookDTO.title());
        book.setAuthor(author);
        book.setLanguage(bookDTO.languages().get(0));
        book.setDownload_count(bookDTO.download_count());
        book.setApi_id(bookDTO.api_id());
        return book;
    }
}
