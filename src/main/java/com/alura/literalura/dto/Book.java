package com.alura.literalura.dto;

import java.util.List;
import java.util.Map;

public record Book(
        Integer id,
        String title,
        List<Author> authors,
        List<String> translators,
        List<String> subjects,
        List<String> bookshelves,
        List<String> languages,
        Boolean copyright,
        String media_type,
        Map<String, String> formats,
        Integer download_count) {
}
