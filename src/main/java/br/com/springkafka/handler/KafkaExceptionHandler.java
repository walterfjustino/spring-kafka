package br.com.springkafka.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.KafkaException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class KafkaExceptionHandler {

  @ExceptionHandler(KafkaException.class)
  public ResponseEntity<String> handleKafkaException(KafkaException ex) {
    // Código para lidar com a exceção
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao processar mensagem Kafka: " + ex.getMessage());
  }
}
