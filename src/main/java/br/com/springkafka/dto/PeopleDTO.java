package br.com.springkafka.dto;

import lombok.Data;

import java.util.List;

@Data
public class PeopleDTO {
  private String name;
  private String cpf;

  private List<String> books;

}
