package dev.sgwrth.orderlogger.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {
	@KafkaListener(topics = "topic0", groupId = "my-group")
	private void listen(String message) {
		System.out.println(message);
	}
}
