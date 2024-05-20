package com.alura.literalura.dto;

import org.junit.jupiter.api.Test;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class BookTest {

    @Test
    public void testBookRecord() {
        AuthorDTO author = new AuthorDTO("United States. Central Intelligence Agency", null, null);
        BookDTO bookRecord = new BookDTO(
                "The 1993 CIA World Factbook",
                1L,
                Collections.singletonList(author),
                Collections.singletonList("en"),
                94);

        assertEquals("The 1993 CIA World Factbook", bookRecord.title());
        assertEquals(1, bookRecord.authors().size());
        assertEquals("United States. Central Intelligence Agency", bookRecord.authors().get(0).name());
        assertNull(bookRecord.authors().get(0).birth_year());
        assertNull(bookRecord.authors().get(0).death_year());
        assertEquals(1, bookRecord.languages().size());
        assertEquals("en", bookRecord.languages().get(0));
        assertEquals(94, bookRecord.download_count());
    }
}
