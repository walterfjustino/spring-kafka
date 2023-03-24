package br.com.springkafka.domain;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class Book {

  @Id
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name = "uuid" , strategy = "uuid2")
  private String id;
  private String name;
  @ManyToOne
  private People people;
}
