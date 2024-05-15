package com.alura.literalura.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.http.HttpResponse;

import static org.junit.jupiter.api.Assertions.*;

class ApiRequestTest {

    private ApiRequest apiRequest;

    @BeforeEach
    void initializeApiRequest(){
        this.apiRequest = new ApiRequest();
    }

    @Test
    void testGetBookById() throws IOException, InterruptedException {
        HttpResponse<String> response =  apiRequest.getBookById(87);
        assertEquals(200, response.statusCode());
    }

    @Test
    void testGetBooksHasBody() throws IOException, InterruptedException {
        HttpResponse<String> response =  apiRequest.getBookById(87);
        assertNotNull(response.body());
    }

}