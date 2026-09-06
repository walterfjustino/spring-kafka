//package br.com.springkafka.exception;
//
//import lombok.Getter;
//import lombok.Setter;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//import java.util.Objects;
//import java.util.stream.Collectors;
//
//
//@Component
//@ConfigurationProperties(prefix = "app.kafka.dlt.excecoes")
//@Slf4j
//public class KafkaExceptionHandler  {
//
//  /**
//   * Exceções classificadas como recuperáveis.
//   * <br>
//   * A configuração é feita no {@code application.properties}, através
//   * das propriedades {@code app.kafka.dlt.excecoes.recuperaveis[]}
//   */
//
//  @Getter
//  @Setter
//  private List<Class<? extends Exception>> retrievable;
//
//  /**
//   * Exceções classificadas como não-recuperáveis.
//   * <br>
//   * A configuração é feita no {@code application.properties}, através
//   * das propriedades {@code app.kafka.dlt.excecoes.naoRecuperaveis[]}
//   */
//
//  @Getter
//  @Setter
//  private List<Class<? extends Exception>> irretrievable;
//
//  @SuppressWarnings("unchecked")
//  private List<Class<? extends Exception>> parse(List<String> exceptions) {
//
//    log.info("Parse da lista de exceptions {}", exceptions);
//
//    return Objects.requireNonNull(exceptions)
//                    .stream()
//                    .map(className -> {
//                      try{
//                        return Class.forName(className);
//
//                      }catch(ClassNotFoundException exception){
//                        throw new RuntimeException(exception);
//                      }
//                    })
//                    .map(ex -> (Class<? extends Exception>)ex)
//                    .collect(Collectors.toList());
//  }
//
//  public List<Class<? extends Exception>> getRetrievable() {
//    return retrievable;
//  }
//
//  public void setRetrievable(List<Class<? extends Exception>> retrievable) {
//    this.retrievable = retrievable;
//  }
//
//  public List<Class<? extends Exception>> getIrretrievable() {
//    return irretrievable;
//  }
//
//  public void setIrretrievable(List<Class<? extends Exception>> irretrievable) {
//    this.irretrievable = irretrievable;
//  }
//
//
//}
////  @KafkaHandler
////  public void bar(Bar bar) {
////    System.out.println("Received: " + bar);
////  }
//
////  @KafkaHandler(isDefault = true)
////  public void unknown(Object object) {
////    log.info("Received: {}", object);
////    System.out.println("Received unknown: " + object);
////  }
//
//
//
////@Slf4j
////@Component
////@KafkaListener(id = "KafkaExceptionHandler", topics = { "People" })
////public class KafkaExceptionHandler  {
////
////  @KafkaHandler
////  public void foo(People people) {
////    log.info("Received: {}", people);
////    System.out.println("Received: " + people);
////  }
////
//////  @KafkaHandler
//////  public void bar(Bar bar) {
//////    System.out.println("Received: " + bar);
//////  }
////
////  @KafkaHandler(isDefault = true)
////  public void unknown(Object object) {
////    log.info("Received: {}", object);
////    System.out.println("Received unknown: " + object);
////  }
////
////  }
//
////  @ExceptionHandler(KafkaException.class)
////  public ResponseEntity<String> handleKafkaException(KafkaException ex) {
////    // Código para lidar com a exceção
////    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao processar mensagem Kafka: " + ex.getMessage());
////  }
//
//
//
////  private void testJson(String name, ArrayList<String> contact){
////    JsonObject body = Json.createObjectBuilder()
////            .add("Pessoa", Json.createObjectBuilder()
////                    .add("name", name)
////                    .add("age", "age")
////                    .add("contact", Json.createArrayBuilder()
////                            .add("celular", "celular")));
////  }