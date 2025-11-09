package dev.sgwrth.orderlogger.service;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import dev.sgwrth.orderlogger.dto.CustomUserDto;

public interface AuthService {

	@Service
	class AuthServiceImpl implements AuthService {
		
		private final AuthenticationManager authenticationManager;
		private final JwtService jwtService;
		private final KafkaTemplate<String, String> kafkaTemplate;

		public AuthServiceImpl(
				AuthenticationManager authenticationManager,
				JwtService jwtService,
				KafkaTemplate<String, String> kafkaTemplate
		) {
			this.authenticationManager = authenticationManager;
			this.jwtService = jwtService;
			this.kafkaTemplate = kafkaTemplate;
		}

		@Override
		public String generateToken(@RequestBody CustomUserDto customUserDto) {
			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(
							customUserDto.getUsername(),
							customUserDto.getPassword()
					)
			);

			if (authentication.isAuthenticated()) {
				this.kafkaTemplate.send("issue-token", "Token issued.");
				return jwtService.generateToken(customUserDto.getUsername());
			} else {
				throw new UsernameNotFoundException("Username not found");
			}

		}

	}

	String generateToken(CustomUserDto customUserDto);

}
