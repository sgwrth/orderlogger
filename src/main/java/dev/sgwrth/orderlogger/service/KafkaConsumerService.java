package dev.sgwrth.orderlogger.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import dev.sgwrth.orderlogger.handler.KafkaMessageHandler;

@Service
public class KafkaConsumerService {
	
	private final KafkaMessageHandler kafkaMessageHandler;
	private final LoggerService loggerService;
	
	public KafkaConsumerService(
			KafkaMessageHandler kafkaMessageHandler,
			LoggerService loggerService
	) {
		this.kafkaMessageHandler = kafkaMessageHandler;
		this.loggerService = loggerService;
	}

	@KafkaListener(topics = "receive-order", groupId = "my-group")
	private void receiveOrder(String message) {
		if (message != null) {
			kafkaMessageHandler.broadcastMessage(message);
		}
	}

	@KafkaListener(topics = "create-user", groupId = "my-group")
	private void createUser(String message) {
		if (message != null) {
			kafkaMessageHandler.broadcastMessage(message);
		}
	}

	@KafkaListener(topics = "issue-token", groupId = "my-group")
	private void issueToken(String message) {
		if (message != null) {
			kafkaMessageHandler.broadcastMessage(message);
		}
	}
	
	@KafkaListener(topics = "log-message", groupId = "my-group")
	private void logMessage(String message) {
		if (message != null) {
			kafkaMessageHandler.broadcastMessage(message);
		}
	}
}
