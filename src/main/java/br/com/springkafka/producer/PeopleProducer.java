package br.com.springkafka.producer;

import br.com.springkafka.People;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PeopleProducer {


  @Value("${topic.name}")
  private String topicName;
  private final KafkaTemplate<String, People> kafkaTemplate;

  // O metodo send utilizado para versão springboot 2x
//  public void sendMessage(People people){
//    kafkaTemplate.send(topicName,(String) people.getId(), people).addCallback(
//            success -> log.info("Mensagem Enviada com sucesso !"),
//            failure -> log.error("Falha ao Enviar Mensagem !")
//    );
//  }

  public void sendMessage(People people) {
    // O metodo send agora retorna um CompletableFuture atualizado para versão springboot 3x
    kafkaTemplate.send(topicName, String.valueOf(people.getId()), people)
            .whenComplete((result, ex) -> {
              if (ex == null) {
                log.info("Mensagem Enviada com sucesso! Offset: {}", result.getRecordMetadata().offset());
              } else {
                log.error("Falha ao Enviar Mensagem!", ex);
              }
            });
  }
}
