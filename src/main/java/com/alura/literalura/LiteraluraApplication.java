package com.alura.literalura;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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

	private void menu(){
		Scanner s = new Scanner(System.in);
		while(true) {
			System.out.println("__________BANCO DE LIVROS GUTENBERK__________");
			System.out.println("Selecione as opções a seguir: ");
			System.out.println("1 - Inserir Autor: ");
			System.out.println("2 - Listar todos os Autores: ");
			System.out.println("0 - Sair \n");
			System.out.println("Insira a opção escolhida: ");
			int selectedOption = s.nextInt();
            if (selectedOption == 0) {
				break;
            }
		}
    }
}
