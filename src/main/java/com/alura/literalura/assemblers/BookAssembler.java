package com.alura.literalura.assemblers;

import com.alura.literalura.dto.Book;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.http.HttpResponse;

public class BookAssembler {

    public Book fromResponseToDTO(HttpResponse<String> response){
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.convertValue(response.body(),Book.class);
    }
}
