package com.alura.literalura.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.alura.literalura.entities.Author;

/**
 * AuthorRepository
 */
@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

  @Query("SELECT a FROM author a WHERE :year BETWEEN a.birth_year AND a.death_year")
  List<Author> findAuthorsAliveInYear(@Param("year") Integer year);
}
