package dev.sgwrth.orderlogger.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

	@Bean
	public NewTopic receiveOrder() {
	    return TopicBuilder.name("receive-order")
	            .build();
	}

	@Bean
	public NewTopic userCreated() {
	    return TopicBuilder.name("create-user")
	            .build();
	}

	@Bean
	public NewTopic tokenIssued() {
	    return TopicBuilder.name("issue-token")
	            .build();
	}
}
