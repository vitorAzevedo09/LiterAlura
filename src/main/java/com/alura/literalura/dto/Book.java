package com.alura.literalura.dto;

public record Book(
        String title,
        String authors,
        String languages,
        Integer download_count) {
}
