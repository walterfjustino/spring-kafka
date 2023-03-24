package br.com.springkafka.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;

@Entity
public class People {

  @Id
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name = "uuid" , strategy = "uuid2")
  private String id;
  private String name;
  private String cpf;
  @OneToMany(mappedBy = "people")
  private List<Book> books;
}
