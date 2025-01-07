package com.github.gbaso.kafka.joiner;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
class MessageProducer {

  private final AtomicLong counter = new AtomicLong(0);
  private final Random random = new Random();
  private final ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor();

  private final KafkaTemplate<String, String> kafkaTemplate;

  MessageProducer(KafkaTemplate<String, String> kafkaTemplate) {
    this.kafkaTemplate = kafkaTemplate;
  }

  @Scheduled(fixedRate = 100, timeUnit = TimeUnit.MILLISECONDS)
  void trigger() {
    executor.submit(this::sendMessage);
  }

  void sendMessage() {
    try {
      String key = String.valueOf(UUID.randomUUID());
      kafkaTemplate.send("first", key, "received");
      long delay = 400L + random.nextLong(0L, 150L);
      Thread.sleep(delay);
      kafkaTemplate.send("second", key, "#" + counter.incrementAndGet());
    } catch (InterruptedException e) {
      // suppress
    }
  }
}
