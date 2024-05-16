package com.alura.literalura;

import com.alura.literalura.api.ApiRequest;
import com.alura.literalura.dto.Book;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@SpringBootApplication
public class LiteraluraApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(LiteraluraApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		menu();
	}

	private void menu() throws IOException, InterruptedException {
		Scanner scanner = new Scanner(System.in);
		ApiRequest apiRequest = new ApiRequest();

		while (true) {
			System.out.println("__________BANCO DE LIVROS GUTENBERG__________");
			System.out.println("Selecione uma opção: ");
			System.out.println("1 - Inserir Autor");
			System.out.println("2 - Listar todos os Autores");
			System.out.println("3 - Pesquisar Por Título");
			System.out.println("0 - Sair \n");
			System.out.print("Insira a opção escolhida: ");

			int selectedOption = scanner.nextInt();
			scanner.nextLine(); // Limpa o buffer do scanner

			switch (selectedOption) {
				case 0:
					return;
				case 3:
					System.out.print("Insira o título do livro que deseja pesquisar: ");
					String title = scanner.nextLine();
					System.out.println("Carregando, por favor espere...");
					List<Book> books = apiRequest.searchByTitle(title);
					if (books.isEmpty()) {
						System.out.println("Livro não encontrado, por favor, tente novamente");
					} else {
						Book firstBook = books.get(0);
						System.out.println("-----------------------------------------------");
						System.out.println("Título do Livro: " + firstBook.title());
						System.out.println("Autor do Livro: " + firstBook.authors().get(0).name());
						System.out.println("Idioma do Livro: " + firstBook.languages().get(0));
						System.out.println("Número de Downloads do Livro: " + firstBook.download_count());
						System.out.println("-----------------------------------------------");
					}
					break;
				default:
					System.out.println("Opção inválida, por favor escolha novamente.");
			}
		}
    }
}
