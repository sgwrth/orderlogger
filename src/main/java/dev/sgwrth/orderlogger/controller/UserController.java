package dev.sgwrth.orderlogger.controller;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.sgwrth.orderlogger.dto.AuthRequestDto;
import dev.sgwrth.orderlogger.dto.CustomUserDto;
import dev.sgwrth.orderlogger.service.CustomUserService;
import dev.sgwrth.orderlogger.service.JwtService;

@RestController
@RequestMapping("/auth")
public class UserController {

	private final CustomUserService customUserService;
	private final JwtService jwtService;
	private final AuthenticationManager authenticationManager;
	
	public UserController(
			CustomUserService customUserService,
			JwtService jwtService,
			AuthenticationManager authenticationManager
	) {
		this.customUserService = customUserService;
		this.jwtService = jwtService;
		this.authenticationManager = authenticationManager;
	}
	
	@PostMapping("/user")
	public String addUser(@RequestBody CustomUserDto customUserDto) {
		return this.customUserService.addUser(customUserDto);
	}
	
	@PostMapping("/token")
	public String generateToken(@RequestBody AuthRequestDto authRequestDto) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						authRequestDto.username(),
						authRequestDto.password()
				)
		);
		if (authentication.isAuthenticated()) {
			return jwtService.generateToken(authRequestDto.username());
		} else {
			throw new UsernameNotFoundException("Username not found");
		}
	}

}
