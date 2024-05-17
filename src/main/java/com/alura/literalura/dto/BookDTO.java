package com.alura.literalura.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record BookDTO(
                String title,
                @JsonProperty("id") Long api_id,
                List<AuthorDTO> authors,
                List<String> languages,
                Integer download_count) {
}
