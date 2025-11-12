package dev.sgwrth.orderlogger.service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

import org.springframework.stereotype.Service;

public interface LoggerService {
	
	@Service
	class LoggerImpl implements LoggerService {
		
		@Override
		public void appendMsg(String message) {
			Path path = this.getPathForLogfile();
			try {
				Files.writeString(
						path,
						message,
						StandardCharsets.UTF_8,
						StandardOpenOption.CREATE,
						StandardOpenOption.APPEND
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
	
	void appendMsg(String message);
	
	Path getPathForLogfile();

}
