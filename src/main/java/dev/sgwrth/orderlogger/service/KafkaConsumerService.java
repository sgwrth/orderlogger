package dev.sgwrth.orderlogger.service;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;


@Service
public class KafkaConsumerService {
	
	private final List<WebSocketSession> sessions = new CopyOnWriteArrayList<>();
	
    public void addSession(WebSocketSession session) {
        sessions.add(session);
    }

    public void removeSession(WebSocketSession session) {
        sessions.remove(session);
    }
    
    @KafkaListener(topics = "receive-order", groupId = "my-group")
    public void listen(String message) {
    	for (WebSocketSession session : sessions) {
    		if (session.isOpen()) {
    			try {
    				System.out.println(message);
    				session.sendMessage(new TextMessage(message));
    			} catch (Exception e) {
    				e.printStackTrace();
    			}
    		}
    	}
    }

}
