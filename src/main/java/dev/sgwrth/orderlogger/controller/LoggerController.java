package dev.sgwrth.orderlogger.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.sgwrth.orderlogger.service.LoggerService;

@RestController
@RequestMapping("/log")
public class LoggerController {
	
	private final LoggerService loggerService;
	
	public LoggerController(LoggerService loggerService) {
		this.loggerService = loggerService;
	}
	
	@PostMapping
	private void logMsg(@RequestParam String message) {
		this.loggerService.appendMsg(message);
	}

}
