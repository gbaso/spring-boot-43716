package com.github.gbaso.kafka.joiner;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Import(TestcontainersConfiguration.class)
@SpringBootTest
class KafkaStreamsJoinerApplicationTests {

  @Test
  void contextLoads() {
  }
}
