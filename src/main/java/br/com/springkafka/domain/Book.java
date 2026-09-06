package br.com.springkafka.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
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
