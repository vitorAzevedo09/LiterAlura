package com.alura.literalura.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * Author
 */
@Entity(name = "author")
public class Author {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long ID;

  @Column(name = "name")
  private String name;

  @Column(name = "birth_year")
  private Integer birth_year;

  @Column(name = "death_year")
  private Integer death_year;

  public Author() {
  }

  public Author(String name, Integer birth_year, Integer death_year) {
    this.name = name;
    this.birth_year = birth_year;
    this.death_year = death_year;
  }

  public Long getID() {
    return ID;
  }

  public void setID(Long iD) {
    ID = iD;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getBirth_year() {
    return birth_year;
  }

  public void setBirth_year(Integer birth_year) {
    this.birth_year = birth_year;
  }

  public Integer getDeath_year() {
    return death_year;
  }

  public void setDeath_year(Integer death_year) {
    this.death_year = death_year;
  }

}
