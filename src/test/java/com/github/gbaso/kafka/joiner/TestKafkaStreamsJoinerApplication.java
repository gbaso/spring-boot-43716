package com.github.gbaso.kafka.joiner;

import org.springframework.boot.SpringApplication;

public class TestKafkaStreamsJoinerApplication {

  public static void main(String[] args) {
    SpringApplication
        .from(KafkaStreamsJoinerApplication::main)
        .with(TestcontainersConfiguration.class)
        .run(args);
  }
}
