package com.github.gbaso.kafka.joiner;

import static java.time.Duration.ofMillis;
import static org.apache.kafka.streams.kstream.JoinWindows.ofTimeDifferenceWithNoGrace;

import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.KStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
class StreamsConfiguration {

  private static final Logger log = LoggerFactory.getLogger(StreamsConfiguration.class);

  @Bean
  KStream<String, String> first(StreamsBuilder builder) {
    return builder.stream("first");
  }

  @Bean
  KStream<String, String> second(StreamsBuilder builder) {
    return builder.stream("second");
  }

  @Bean
  KStream<String, String> joiner(KStream<String, String> first, KStream<String, String> second) {
    KStream<String, String> result = first
        .join(second, (u, v) -> u + " " + v, ofTimeDifferenceWithNoGrace(ofMillis(500L)))
        .peek((key, value) -> log.info(value));
    result.to("result");
    return result;
  }
}
