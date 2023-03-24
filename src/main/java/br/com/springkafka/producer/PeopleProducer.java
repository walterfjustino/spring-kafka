package br.com.springkafka.producer;

import br.com.springkafka.People;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.avro.AvroMissingFieldException;
import org.apache.avro.AvroRuntimeException;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.KafkaException;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
//@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PeopleProducer {

//  @Value("${topic.name}")
//  private String topicName;
//
//  private KafkaTemplate<String, People> kafkaTemplate;


  private final KafkaTemplate<String, People> kafkaTemplate;
  private final String topicName;

  public PeopleProducer(@Value("${topic.name}") String topicName, KafkaTemplate<String, People> kafkaTemplate) {
    this.topicName = topicName;
    this.kafkaTemplate = kafkaTemplate;
  }

public void sendMessage(People people) {
  ProducerRecord<String, People> record = new ProducerRecord<>(topicName, people);

//  var futureMessage = kafkaTemplate.send(topicName, (String) people.getId(), people)
  try {
    var futureMessage = kafkaTemplate.send(record)
            .thenAccept(recordMetadata -> {
              log.info("Sent message={} to topic={} partition={}", people, topicName, recordMetadata.getRecordMetadata().partition());
            }).exceptionally(ex -> {
              log.info("Received exception {}, throwing new exception!", ex.getMessage());
              throw new IllegalArgumentException();
            });
  } catch (KafkaException | AvroRuntimeException exception) {
    exception.getMessage();
  }

}
}

//  public void sendMessageAsync(String topicName, People people) {
//    ProducerRecord<String, People> record = new ProducerRecord<>(topicName, people);
//    var futureMessage = kafkaTemplate.send(record)
//            .toCompletableFuture()
//            .thenAccept(getSendResult(topicName, people));
//    futureMessage.exceptionally(PeopleProducer::exceptionThrow);
//  }
//
//  private static Consumer<SendResult<String, People>> getSendResult(String topicName, People people) {
//    return recordMetadata -> {
//      log.info("Sended message={} to topic={} partition={}", people, topicName, recordMetadata.getRecordMetadata().partition());
//    };
//  }
//
//  private static Void exceptionThrow(Throwable ex) {
//    log.error("Received exception {}, throwing new exception!", ex.getMessage());
//    throw new KafkaException(ex.getMessage());
//  }
//
//}

//  public void sendMessageAsync(String topicName, People people) {
//    ProducerRecord<String, People> record = new ProducerRecord<>(topicName, people);
//    var futureMessage = kafkaTemplate.send(record)
//            .toCompletableFuture()
//            .thenAccept(recordMetadata -> { log.info("Sent message={} to topic={} partition={}", people, topicName, recordMetadata.getRecordMetadata().partition());});
//     futureMessage.exceptionallyCompose(throwable -> {
//       Throwable causeError = throwable.getCause();
//       return null;
//     });
//  }
//  public void sendMessage(People people) {
//    CompletableFuture<SendResult<String, People>> sendFuture = kafkaTemplate.send(topicName, people);
//    var record = new ProducerRecord<String, People>(topicName, people);
//      sendFuture.completeAsync(() -> {
//        try {
//          return this.onSuccess();
//        } catch (InterruptedException | ExecutionException e) {
//          throw new RuntimeException(e);
//        }
//      }).thenApply(result -> record)
//
//  }