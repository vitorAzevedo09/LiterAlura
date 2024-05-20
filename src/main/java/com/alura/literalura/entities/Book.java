package com.alura.literalura.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

/**
 * Book
 */
@Entity(name = "book")
public class Book {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long ID;

  @Column(name = "api_id")
  private Long apiId;

  @Column(name = "title")
  private String title;

  @ManyToOne(targetEntity = Author.class, cascade = CascadeType.ALL)
  private Author author;

  @Column(name = "language")
  private String language;

  @Column(name = "download_count")
  private Integer download_count;

  public Book() {
  }

  public Book(String title, Author author, String language, Integer download_count) {
    this.title = title;
    this.author = author;
    this.language = language;
    this.download_count = download_count;
  }

  public Long getID() {
    return ID;
  }

  public void setID(Long iD) {
    ID = iD;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Author getAuthor() {
    return author;
  }

  public void setAuthor(Author author) {
    this.author = author;
  }

  public String getLanguage() {
    return language;
  }

  public void setLanguage(String language) {
    this.language = language;
  }

  public Integer getDownload_count() {
    return download_count;
  }

  public void setDownload_count(Integer download_count) {
    this.download_count = download_count;
  }

  public String getAuthorName() {
    return author.getName();
  }

  public Long getApi_id() {
    return apiId;
  }

  public void setApi_id(Long api_id) {
    this.apiId = api_id;
  }

  public void setAuthorName(String authorName) {
    this.author.setName(authorName);
  }

}
