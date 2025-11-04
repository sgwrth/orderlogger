package dev.sgwrth.orderlogger.handler;

import org.springframework.lang.NonNull;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import dev.sgwrth.orderlogger.service.KafkaConsumerService;

public class KafkaMessageHandler extends TextWebSocketHandler {

	private final KafkaConsumerService kafkaConsumerService;
	
	public KafkaMessageHandler(KafkaConsumerService kafkaConsumerService) {
		this.kafkaConsumerService = kafkaConsumerService;
	}
	
	public void afterConnectionEstablished(@NonNull WebSocketSession session) {
		kafkaConsumerService.addSession(session);
	}

	public void afterConnectionClosed(WebSocketSession session) {
		kafkaConsumerService.removeSession(session);
	}
}
