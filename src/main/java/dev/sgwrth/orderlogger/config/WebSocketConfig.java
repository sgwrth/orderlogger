package dev.sgwrth.orderlogger.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import dev.sgwrth.orderlogger.handler.KafkaMessageHandler;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {
	
    @Override
    public void registerWebSocketHandlers(
        WebSocketHandlerRegistry webSocketHandlerRegistry)
    {
        webSocketHandlerRegistry
            .addHandler(this.kafkaMessageHandler(), "/ws/kafka")
            .setAllowedOrigins("*");
    }
    
    @Bean
    public KafkaMessageHandler kafkaMessageHandler() {
    	return new KafkaMessageHandler();
    }
	
}
