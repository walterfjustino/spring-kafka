package br.com.springkafka.controller;

import br.com.springkafka.People;
import br.com.springkafka.dto.PeopleDTO;
import br.com.springkafka.producer.PeopleProducer;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/peoples")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PeopleController {

  @Autowired
  private PeopleProducer peopleProducer;

  @PostMapping
  public ResponseEntity<Void> sendMessage(@RequestBody PeopleDTO peopleDTO){
    var id = UUID.randomUUID().toString();

    var message = People.newBuilder()
            .setId(id)
            .setName(peopleDTO.getName())
            .setCpf(peopleDTO.getCpf())
            .setBooks(peopleDTO.getBooks()
                    .stream()
                    .map(p -> (CharSequence) p)
                    .collect(Collectors.toList()))
            .build();
    peopleProducer.sendMessage(message);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }
}
