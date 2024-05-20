package com.alura.literalura;

import com.alura.literalura.entities.Author;
import com.alura.literalura.entities.Book;
import com.alura.literalura.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class LiteraluraApplicationTests {

	@Mock
	private BookService bookService;

	@InjectMocks
	private LiteraluraApplication literaluraApplication;

	@BeforeEach
	public void setup() {
		literaluraApplication = new LiteraluraApplication();
		literaluraApplication.bookService = bookService;
	}

	@Test
	public void testSearchBookNotFound() throws IOException, InterruptedException {
		when(bookService.searchByTitle("Nonexistent Title")).thenReturn(Optional.empty());

		Scanner scanner = new Scanner("Nonexistent Title\n");
		literaluraApplication.searchBook(scanner);

		verify(bookService, times(1)).searchByTitle("Nonexistent Title");
	}

	@Test
	public void testListAllAuthors() throws IOException, InterruptedException {
		Author author1 = new Author();
		author1.setName("Author 1");
		author1.setBirth_year(1950);
		author1.setDeath_year(2000);

		Author author2 = new Author();
		author2.setName("Author 2");
		author2.setBirth_year(1970);
		author2.setDeath_year(null);

		List<Author> authors = Arrays.asList(author1, author2);
		when(bookService.getAllAuthorsInDB()).thenReturn(authors);

		literaluraApplication.listAllAuthors();

		verify(bookService, times(1)).getAllAuthorsInDB();
	}

	@Test
	public void testListAuthorsAliveInYear() throws IOException, InterruptedException {
		Author author = new Author();
		author.setName("Author 1");
		author.setBirth_year(1950);
		author.setDeath_year(2000);

		List<Author> authors = Arrays.asList(author);
		when(bookService.getAllAuthorsInDBAliveInYear(1980)).thenReturn(authors);

		Scanner scanner = new Scanner("1980\n");
		literaluraApplication.listAuthorsAliveInYear(scanner);

		verify(bookService, times(1)).getAllAuthorsInDBAliveInYear(1980);
	}

	@Test
	public void testListBooksByLanguageEnglish() {
		when(bookService.quantityBooksByLanguage("en")).thenReturn(100);

		Scanner scanner = new Scanner("1\n");
		literaluraApplication.listBooksByLanguage(scanner);

		verify(bookService, times(1)).quantityBooksByLanguage("en");
	}

	@Test
	public void testListBooksByLanguageFrench() {
		when(bookService.quantityBooksByLanguage("fr")).thenReturn(50);

		Scanner scanner = new Scanner("2\n");
		literaluraApplication.listBooksByLanguage(scanner);

		verify(bookService, times(1)).quantityBooksByLanguage("fr");
	}
}
