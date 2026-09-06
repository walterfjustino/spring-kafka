package br.com.springkafka.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class People {

  @Id
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name = "uuid" , strategy = "uuid2")
  private String id;
  private String name;
  private String cpf;
  @OneToMany(mappedBy = "people", cascade = CascadeType.ALL)
  private List<Book> books;
}
