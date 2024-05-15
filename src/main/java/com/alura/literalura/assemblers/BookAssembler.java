package com.alura.literalura.assemblers;

import com.alura.literalura.dto.Book;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.http.HttpResponse;

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
}
