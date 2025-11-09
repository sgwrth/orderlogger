package dev.sgwrth.orderlogger.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.sgwrth.orderlogger.dto.CustomUserDto;
import dev.sgwrth.orderlogger.service.AuthService;
import dev.sgwrth.orderlogger.service.CustomUserService;

@RestController
@RequestMapping("/auth")
public class UserController {

	private final CustomUserService customUserService;
	private final AuthService authService;
	
	public UserController(
			CustomUserService customUserService,
			AuthService authService
	) {
		this.customUserService = customUserService;
		this.authService = authService;
	}
	
	@PostMapping("/user")
	public String addUser(@RequestBody CustomUserDto customUserDto) {
		return this.customUserService.addUser(customUserDto);
	}
	
	@PostMapping("/token")
	public String generateToken(@RequestBody CustomUserDto customUserDto) {
		return this.authService.generateToken(customUserDto);
	}

}
