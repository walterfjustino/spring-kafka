package br.com.springkafka.consumer;


import br.com.springkafka.People;
import br.com.springkafka.domain.Book;
import br.com.springkafka.repository.PeopleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class PeopleConsumer {

  private final PeopleRepository peopleRepository;

  @KafkaListener(topics = "${topic.name}")
  public void consumer(ConsumerRecord<String, People> consumerRecord, Acknowledgment ack) {

    var people = consumerRecord.value();

    log.info("Mensagem consumida " + people.toString());

    var peopleEntity = br.com.springkafka.domain.People.builder().build();

    peopleEntity.setId(people.getId().toString());
    peopleEntity.setCpf(people.getCpf().toString());
    peopleEntity.setName(people.getName().toString());
    peopleEntity.setBooks(people.getBooks().stream()
            .map(book -> Book.builder()
                    .people(peopleEntity)
                    .name(book.toString())
                    .build())
            .toList());

    peopleRepository.save(peopleEntity);
    log.info("Mensagem salva " + people.toString());

    ack.acknowledge();

  }
}
