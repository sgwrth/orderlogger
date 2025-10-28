package dev.sgwrth.orderlogger.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {
	@Bean
	public NewTopic topic0() {
	    return TopicBuilder.name("topic0")
	            .build();
	}
}
