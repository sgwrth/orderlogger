package dev.sgwrth.orderlogger.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import dev.sgwrth.orderlogger.handler.KafkaMessageHandler;

@Service
public class KafkaConsumerService {
	
	private final KafkaMessageHandler kafkaMessageHandler;
	
	public KafkaConsumerService(KafkaMessageHandler kafkaMessageHandler) {
		this.kafkaMessageHandler = kafkaMessageHandler;
	}

	@KafkaListener(topics = "receive-order", groupId = "my-group")
	private void receiveOrder(String message) {
		this.broadcastMsg(message);
	}

	@KafkaListener(topics = "create-user", groupId = "my-group")
	private void createUser(String message) {
		this.broadcastMsg(message);
	}

	@KafkaListener(topics = "issue-token", groupId = "my-group")
	private void issueToken(String message) {
		this.broadcastMsg(message);
	}
	
	@KafkaListener(topics = "log-message", groupId = "my-group")
	private void logMessage(String message) {
		this.broadcastMsg(message);
	}

	private void broadcastMsg(String message) {
		if (message != null) {
			this.kafkaMessageHandler.broadcastMessage(message);
		}
	}
}
