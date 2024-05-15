package com.alura.literalura.dto;

import org.junit.jupiter.api.Test;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class BookTest {

    @Test
    public void testBookRecord() {
        Author author = new Author("United States. Central Intelligence Agency", null, null);
        HashMap<String, String> formats = new HashMap<>();
        formats.put("text/html", "https://www.gutenberg.org/ebooks/87.html.images");
        formats.put("application/epub+zip", "https://www.gutenberg.org/ebooks/87.epub3.images");
        formats.put("application/x-mobipocket-ebook", "https://www.gutenberg.org/ebooks/87.kf8.images");
        formats.put("application/rdf+xml", "https://www.gutenberg.org/ebooks/87.rdf");
        formats.put("image/jpeg", "https://www.gutenberg.org/cache/epub/87/pg87.cover.medium.jpg");
        formats.put("application/octet-stream", "https://www.gutenberg.org/cache/epub/87/pg87-h.zip");
        formats.put("text/plain; charset=us-ascii", "https://www.gutenberg.org/ebooks/87.txt.utf-8");

        Book bookRecord = new Book(
                87,
                "The 1993 CIA World Factbook",
                Collections.singletonList(author),
                Collections.emptyList(),
                List.of("Geography -- Handbooks, manuals, etc.", "Political science -- Handbooks, manuals, etc.", "Political statistics -- Handbooks, manuals, etc.", "World politics -- Handbooks, manuals, etc."),
                Collections.singletonList("CIA World Factbooks"),
                Collections.singletonList("en"),
                false,
                "Text",
                formats,
                94
        );

        assertEquals(87, bookRecord.id());
        assertEquals("The 1993 CIA World Factbook", bookRecord.title());
        assertEquals(1, bookRecord.authors().size());
        assertEquals("United States. Central Intelligence Agency", bookRecord.authors().get(0).name());
        assertNull(bookRecord.authors().get(0).birth_year());
        assertNull(bookRecord.authors().get(0).death_year());
        assertEquals(0, bookRecord.translators().size());
        assertEquals(4, bookRecord.subjects().size());
        assertEquals("Geography -- Handbooks, manuals, etc.", bookRecord.subjects().get(0));
        assertEquals(1, bookRecord.bookshelves().size());
        assertEquals("CIA World Factbooks", bookRecord.bookshelves().get(0));
        assertEquals(1, bookRecord.languages().size());
        assertEquals("en", bookRecord.languages().get(0));
        assertEquals(false, bookRecord.copyright());
        assertEquals("Text", bookRecord.media_type());
        assertEquals(7, bookRecord.formats().size());
        assertEquals("https://www.gutenberg.org/ebooks/87.html.images", bookRecord.formats().get("text/html"));
        assertEquals("https://www.gutenberg.org/ebooks/87.epub3.images", bookRecord.formats().get("application/epub+zip"));
        assertEquals("https://www.gutenberg.org/ebooks/87.kf8.images", bookRecord.formats().get("application/x-mobipocket-ebook"));
        assertEquals("https://www.gutenberg.org/ebooks/87.rdf", bookRecord.formats().get("application/rdf+xml"));
        assertEquals("https://www.gutenberg.org/cache/epub/87/pg87.cover.medium.jpg", bookRecord.formats().get("image/jpeg"));
        assertEquals("https://www.gutenberg.org/cache/epub/87/pg87-h.zip", bookRecord.formats().get("application/octet-stream"));
        assertEquals("https://www.gutenberg.org/ebooks/87.txt.utf-8", bookRecord.formats().get("text/plain; charset=us-ascii"));
        assertEquals(94, bookRecord.download_count());
    }
}
