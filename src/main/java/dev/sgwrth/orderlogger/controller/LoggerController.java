package dev.sgwrth.orderlogger.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.sgwrth.orderlogger.dto.LoggerMessageDto;
import dev.sgwrth.orderlogger.service.LoggerService;

@RestController
@RequestMapping("/log")
public class LoggerController {
	
	private final LoggerService loggerService;
	
	public LoggerController(LoggerService loggerService) {
		this.loggerService = loggerService;
	}
	
	@PostMapping
	private void logMsg(@RequestBody LoggerMessageDto loggerMsgDto) {
		this.loggerService.appendMsg(loggerMsgDto);
	}

}
