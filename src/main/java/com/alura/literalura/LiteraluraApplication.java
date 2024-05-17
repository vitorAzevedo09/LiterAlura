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
	private BookService bookService;

	public static void main(String[] args) {
		SpringApplication.run(LiteraluraApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		menu();
	}

	private void menu() throws IOException, InterruptedException {
		Scanner scanner = new Scanner(System.in);
		int selectedOption = -1;
		while (selectedOption != 0) {
			System.out.println("__________BANCO DE LIVROS GUTENBERG__________");
			System.out.println("Selecione uma opção: ");
			System.out.println("1 - Pesquisar Por Título");
			System.out.println("2 - Listar todos os Livros já buscados");
			System.out.println("3 - Listar todos os Autores de Livros já buscados");
			System.out.println("4 - Listar todos os Autores de Livros já buscados vivos em um ano");
			System.out.println("0 - Sair \n");
			System.out.print("Insira a opção escolhida: ");

			selectedOption = scanner.nextInt();
			scanner.nextLine(); // Limpa o buffer do scanner

			switch (selectedOption) {
				case 0:
					break;
				case 1:
					searchBook(scanner);
					break;
				case 2:
					showAllBooksInDB();
					break;
				case 3:
					showAllAuthorsInDB();
					break;
				case 4:
					showAllAuthorsAliveInDB(scanner);
					break;
				default:
					System.out.println("Opção inválida, por favor escolha novamente.");
			}
		}
		scanner.close();
	}

	private void searchBook(Scanner scanner) throws IOException, InterruptedException {

		System.out.print("Insira o título do livro que deseja pesquisar: ");
		String title = scanner.nextLine();
		System.out.println("Carregando, por favor espere...");
		Optional<Book> book = bookService.searchByTitle(title);
		if (book.isEmpty()) {
			System.out.println("Livro não encontrado, por favor, tente novamente");
		} else {
			Book firstBook = book.get();
			System.out.println("-----------------------------------------------");
			System.out.println("Título do Livro: " + firstBook.getTitle());
			System.out.println("Autor do Livro: " + firstBook.getAuthorName());
			System.out.println("Idioma do Livro: " + firstBook.getLanguage());
			System.out.println("Número de Downloads do Livro: " + firstBook.getDownload_count());
			System.out.println("-----------------------------------------------");
		}
	}

	private void showAllBooksInDB() throws IOException, InterruptedException {
		System.out.println("Carregando, por favor espere...");
		List<Book> books = bookService.getAllBooksInDB();
		for (Book book : books) {
			System.out.println("-----------------------------------------------");
			System.out.println("Título do Livro: " + book.getTitle());
			System.out.println("Autor do Livro: " + book.getAuthorName());
			System.out.println("Idioma do Livro: " + book.getLanguage());
			System.out.println("Número de Downloads do Livro: " + book.getDownload_count());
			System.out.println("-----------------------------------------------");
		}
	}

	private void templateAuthors(List<Author> authors) {
		System.out.println("Carregando, por favor espere...");
		for (Author author : authors) {
			System.out.println("-----------------------------------------------");
			System.out.println("Autor do Livro: " + author.getName());
			System.out.println("Ano de Nascimento: " + author.getBirth_year());
			System.out.println("Ano de Falecimento: " + author.getDeath_year());
			System.out.println("-----------------------------------------------");
		}
	}

	private void showAllAuthorsInDB() throws IOException, InterruptedException {
		List<Author> authors = bookService.getAllAuthorsInDB();
		templateAuthors(authors);
	}

	private void showAllAuthorsAliveInDB(Scanner scanner) throws IOException, InterruptedException {
		System.out.println("Digite o ano escolhido: ");
		Integer year = scanner.nextInt();
		List<Author> authors = bookService.getAllAuthorsInDBAliveInYear(year);
		templateAuthors(authors);
	}
}
