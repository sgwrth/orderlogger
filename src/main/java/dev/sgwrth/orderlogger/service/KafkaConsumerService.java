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
		System.out.println(message);
		kafkaMessageHandler.broadcastMessage(message);
	}

	@KafkaListener(topics = "create-user", groupId = "my-group")
	private void createUser(String message) {
		System.out.println(message);
		kafkaMessageHandler.broadcastMessage(message);
	}

	@KafkaListener(topics = "issue-token", groupId = "my-group")
	private void issueToken(String message) {
		System.out.println(message);
		kafkaMessageHandler.broadcastMessage(message);
	}
}
