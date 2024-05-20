package com.alura.literalura.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alura.literalura.api.ApiRequest;
import com.alura.literalura.assemblers.BookAssembler;
import com.alura.literalura.dto.BookDTO;
import com.alura.literalura.entities.Author;
import com.alura.literalura.entities.Book;
import com.alura.literalura.repository.AuthorRepository;
import com.alura.literalura.repository.BookRepository;

/**
 * BookService
 */
@Service
public class BookService {

  private ApiRequest apiRequest;
  private BookAssembler bookAssembler;
  private BookRepository bookRepository;
  private AuthorRepository authorRepository;

  public BookService() {
  }

  @Autowired
  BookService(BookRepository bookRepository, AuthorRepository authorRepository) {
    this.apiRequest = new ApiRequest();
    this.bookAssembler = new BookAssembler();
    this.bookRepository = bookRepository;
    this.authorRepository = authorRepository;
  }

  public Optional<Book> searchByTitle(final String title) throws IOException, InterruptedException {
    try {

      BookDTO bookFromApi = apiRequest.searchByTitle(title).get(0);
      if (bookFromApi == null) {
        return Optional.empty();
      }
      Book book = bookAssembler.fromDTOToEntity(bookFromApi);
      if (!bookRepository.existsByApiId(bookFromApi.api_id())) {
        bookRepository.save(book);
      }
      return Optional.of(book);
    } catch (Exception e) {
      return Optional.empty();
    }
  }

  public List<Book> getAllBooksInDB() throws IOException, InterruptedException {
    return bookRepository.findAll();
  }

  public List<Author> getAllAuthorsInDB() {
    return authorRepository.findAll();
  }

  public List<Author> getAllAuthorsInDBAliveInYear(Integer year) {
    return authorRepository.findAuthorsAliveInYear(year);
  }

  public Integer quantityBooksByLanguage(final String language) {
    return bookRepository.countByLanguage(language);
  }

}
