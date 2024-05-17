package com.alura.literalura.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alura.literalura.api.ApiRequest;
import com.alura.literalura.assemblers.BookAssembler;
import com.alura.literalura.dto.BookDTO;
import com.alura.literalura.entities.Book;
import com.alura.literalura.repository.BookRepository;

/**
 * BookService
 */
@Service
public class BookService {

  private ApiRequest apiRequest;
  private BookAssembler bookAssembler;
  private BookRepository bookRepository;

  public BookService() {
  }

  @Autowired
  BookService(BookRepository bookRepository) {
    this.apiRequest = new ApiRequest();
    this.bookAssembler = new BookAssembler();
    this.bookRepository = bookRepository;
  }

  public Optional<Book> searchByTitle(final String title) throws IOException, InterruptedException {
    BookDTO bookFromApi = apiRequest.searchByTitle(title).get(0);
    if (bookFromApi == null) {
      return Optional.empty();
    }
    Book book = bookAssembler.fromDTOToEntity(bookFromApi);
    if (!bookRepository.existsByApiId(bookFromApi.api_id())) {
      bookRepository.save(book);
    }
    return Optional.of(book);
  }

  public List<Book> getAllBooksInDB() throws IOException, InterruptedException {
    return bookRepository.findAll();
  }

}
