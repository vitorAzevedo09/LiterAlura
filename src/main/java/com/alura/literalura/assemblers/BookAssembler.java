package com.alura.literalura.assemblers;

import com.alura.literalura.dto.Book;
import com.alura.literalura.dto.ResponseFromSearch;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BookAssembler {

    public Book fromResponseToDTO(HttpResponse<String> response)  {
        ObjectMapper objectMapper = new ObjectMapper();
        String body = response.body();
        try {
            return objectMapper.readValue(body,Book.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Book> fromResponseToListDTO(HttpResponse<String> response)  {
        ObjectMapper objectMapper = new ObjectMapper();
        String body = response.body();
        try {
            ResponseFromSearch responseDTO = objectMapper.readValue(body, ResponseFromSearch.class);
            return responseDTO.results();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
