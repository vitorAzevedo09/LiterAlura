package com.alura.literalura.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BookTest {

    @Test
    void testBookRecord() {

        Book book = new Book("The Great Gatsby", "F. Scott Fitzgerald", "English", 1000);


        assertEquals("The Great Gatsby", book.title());
        assertEquals("F. Scott Fitzgerald", book.authors());
        assertEquals("English", book.languages());
        assertEquals(Integer.valueOf(1000), book.download_count());

    }
}