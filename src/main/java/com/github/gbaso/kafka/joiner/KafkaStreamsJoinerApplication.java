package com.github.gbaso.kafka.joiner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafkaStreams;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableKafkaStreams
@EnableScheduling
public class KafkaStreamsJoinerApplication {

  public static void main(String[] args) {
    SpringApplication.run(KafkaStreamsJoinerApplication.class, args);
  }
}
