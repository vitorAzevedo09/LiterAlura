package com.alura.literalura;

import com.alura.literalura.entities.Author;
import com.alura.literalura.entities.Book;
import com.alura.literalura.service.BookService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@SpringBootApplication
public class LiteraluraApplication implements CommandLineRunner {

	@Autowired
	public BookService bookService;

	public static void main(String[] args) {
		SpringApplication.run(LiteraluraApplication.class, args);
	}

	@Override
	public void run(String... args) {
		try {
			displayMenu();
		} catch (IOException | InterruptedException e) {
			System.err.println("An error occurred while running the application: " + e.getMessage());
		}
	}

	public void displayMenu() throws IOException, InterruptedException {
		Scanner scanner = new Scanner(System.in);
		int selectedOption = -1;

		while (selectedOption != 0) {
			printMenu();
			selectedOption = scanner.nextInt();
			scanner.nextLine(); // Clear the scanner buffer

			switch (selectedOption) {
				case 0 -> System.out.println("Exiting...");
				case 1 -> searchBook(scanner);
				case 2 -> listAllBooks();
				case 3 -> listAllAuthors();
				case 4 -> listAuthorsAliveInYear(scanner);
				case 5 -> listBooksByLanguage(scanner);
				default -> System.out.println("Invalid option, please try again.");
			}
		}
		scanner.close();
	}

	public void printMenu() {
		System.out.println("__________BANCO DE LIVROS GUTENBERG__________");
		System.out.println("Select an option: ");
		System.out.println("1 - Search by Title");
		System.out.println("2 - List all Searched Books");
		System.out.println("3 - List all Authors of Searched Books");
		System.out.println("4 - List all Living Authors in a Given Year");
		System.out.println("5 - List Books by Language");
		System.out.println("0 - Exit \n");
		System.out.print("Enter your choice: ");
	}

	public void searchBook(Scanner scanner) throws IOException, InterruptedException {
		System.out.print("Enter the book title to search: ");
		String title = scanner.nextLine();
		System.out.println("Loading, please wait...");

		Optional<Book> book = bookService.searchByTitle(title);
		if (book.isPresent()) {
			displayBookDetails(book.get());
		} else {
			System.out.println("Book not found, please try again.");
		}
	}

	public void displayBookDetails(Book book) {
		System.out.println("-----------------------------------------------");
		System.out.println("Book Title: " + book.getTitle());
		System.out.println("Book Author: " + book.getAuthorName());
		System.out.println("Book Language: " + book.getLanguage());
		System.out.println("Book Downloads: " + book.getDownload_count());
		System.out.println("-----------------------------------------------");
	}

	public void listAllBooks() throws IOException, InterruptedException {
		System.out.println("Loading, please wait...");
		List<Book> books = bookService.getAllBooksInDB();
		for (Book book : books) {
			displayBookDetails(book);
		}
	}

	public void listAllAuthors() throws IOException, InterruptedException {
		System.out.println("Loading, please wait...");
		List<Author> authors = bookService.getAllAuthorsInDB();
		displayAuthorDetails(authors);
	}

	public void listAuthorsAliveInYear(Scanner scanner) throws IOException, InterruptedException {
		System.out.print("Enter the year: ");
		int year = scanner.nextInt();
		System.out.println("Loading, please wait...");

		List<Author> authors = bookService.getAllAuthorsInDBAliveInYear(year);
		displayAuthorDetails(authors);
	}

	public void displayAuthorDetails(List<Author> authors) {
		for (Author author : authors) {
			System.out.println("-----------------------------------------------");
			System.out.println("Author Name: " + author.getName());
			System.out.println("Birth Year: " + author.getBirth_year());
			System.out.println("Death Year: " + author.getDeath_year());
			System.out.println("-----------------------------------------------");
		}
	}

	public void listBooksByLanguage(Scanner scanner) {
		System.out.println("Choose the language to view:");
		System.out.println("1 - English");
		System.out.println("2 - French");
		int languageChoice = scanner.nextInt();

		switch (languageChoice) {
			case 1 -> System.out
					.println(String.format("\n\n\nThere are %d books written in English\n\n\n",
							bookService.quantityBooksByLanguage("en")));
			case 2 -> System.out
					.println(String.format("\n\n\nThere are %d books written in French\n\n\n",
							bookService.quantityBooksByLanguage("fr")));
			default -> System.out.println("Invalid choice, please try again.");
		}
	}
}
