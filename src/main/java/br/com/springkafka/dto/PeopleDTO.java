package br.com.springkafka.dto;

import java.util.List;

public record PeopleDTO(String name, String cpf, List<String> books) {
}
