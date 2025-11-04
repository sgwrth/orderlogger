package dev.sgwrth.orderlogger.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import dev.sgwrth.orderlogger.handler.KafkaMessageHandler;
import dev.sgwrth.orderlogger.service.KafkaConsumerService;

@Configuration
public class WebSocketConfig implements WebSocketConfigurer {
	
	private final KafkaConsumerService kafkaConsumerService;
	
	public WebSocketConfig(KafkaConsumerService kafkaConsumerService) {
		this.kafkaConsumerService = kafkaConsumerService;
	}

	public void registerWebSocketHandlers(@NonNull WebSocketHandlerRegistry registry) {
		registry.addHandler(new KafkaMessageHandler(this.kafkaConsumerService), "/ws/messages")
			.setAllowedOrigins("*");
	}
	
}
