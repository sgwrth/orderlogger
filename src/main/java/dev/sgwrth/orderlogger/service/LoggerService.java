package dev.sgwrth.orderlogger.service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import dev.sgwrth.orderlogger.dto.LoggerMessageDto;

public interface LoggerService {
	
	@Service
	class LoggerServiceImpl implements LoggerService {

		private final KafkaTemplate<String, String> kafkaTemplate;

		public LoggerServiceImpl(KafkaTemplate<String, String> kafkaTemplate) {
			this.kafkaTemplate = kafkaTemplate;
		}
		
		@Override
		public void appendMsg(LoggerMessageDto loggerMsgDto) {
			Path path = this.getPathForLogfile();
			try {
				String date = LocalDateTime.now().toString();
				Files.writeString(
						path,
						"[" + date + "] " + loggerMsgDto.message() + "\n",
						StandardCharsets.UTF_8,
						StandardOpenOption.CREATE,
						StandardOpenOption.APPEND
				);
				this.kafkaTemplate.send("log-message", "[" + date + "] "
					+ ": Log message: \""
					+ loggerMsgDto.message()
					+ "\""
				);		
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		@Override
		public Path getPathForLogfile() {
			return Path.of("./logfile.log");
		}
	}
	
	void appendMsg(LoggerMessageDto loggerMsgDto);
	
	Path getPathForLogfile();

}
