package com.github.gbaso.kafka.joiner;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaAdmin.NewTopics;
import org.testcontainers.kafka.KafkaContainer;
import org.testcontainers.utility.DockerImageName;

@TestConfiguration(proxyBeanMethods = false)
class TestcontainersConfiguration {

  @Bean
  @ServiceConnection
  KafkaContainer kafkaContainer() {
    return new KafkaContainer(DockerImageName.parse("apache/kafka-native:3.9.0"))
        // https://github.com/testcontainers/testcontainers-java/issues/9506
        .withEnv("KAFKA_LISTENERS", "PLAINTEXT://:9092,BROKER://:9093,CONTROLLER://:9094");
  }

  @Bean
  NewTopics topics() {
    return new NewTopics(
        new NewTopic("first", 1, (short) 1),
        new NewTopic("second", 1, (short) 1),
        new NewTopic("result", 1, (short) 1));
  }
}
