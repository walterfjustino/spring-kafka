package br.com.springkafka.controller;

import br.com.springkafka.People;
import br.com.springkafka.dto.PeopleDTO;
import br.com.springkafka.producer.PeopleProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/peoples")
@RequiredArgsConstructor
public class PeopleController {

  private final PeopleProducer peopleProducer;

  @PostMapping
  public ResponseEntity<Void> sendMessage(@RequestBody PeopleDTO peopleDTO){
    var id = UUID.randomUUID().toString();

    var message = People.newBuilder()
            .setId(id)
            .setName(peopleDTO.name())
            .setCpf(peopleDTO.cpf())
            .setBooks(peopleDTO.books()
                    .stream()
                    .map(p -> (CharSequence) p)
                    .toList())
            .build();

    peopleProducer.sendMessage(message);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }
}
