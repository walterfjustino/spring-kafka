package br.com.springkafka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.scheduling.annotation.EnableAsync;


@SpringBootApplication
public class SpringkafkaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringkafkaApplication.class, args);
	}

}
