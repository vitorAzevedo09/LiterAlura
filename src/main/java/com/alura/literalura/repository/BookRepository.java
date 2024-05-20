package com.alura.literalura.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alura.literalura.entities.Book;

/**
 * BookRepository
 */
@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

  boolean existsByApiId(Long apiId);

  Book findByApiId(Long apiId);

  Integer countByLanguage(final String language);

}
