package dev.sgwrth.orderlogger.service;

import org.springframework.stereotype.Service;

import dev.sgwrth.orderlogger.dto.CustomUserDto;
import dev.sgwrth.orderlogger.entity.CustomUser;
import dev.sgwrth.orderlogger.repository.CustomUserRepository;

public interface CustomUserService {

	@Service
	class CustomUserServiceImpl implements CustomUserService {
		
		private final CustomUserRepository customUserRepository;
		
		public CustomUserServiceImpl(CustomUserRepository customUserRepository) {
			this.customUserRepository = customUserRepository;
		}
		
		@Override
		public String addUser(CustomUserDto customUserDto) {
			final var username = customUserDto.username();
			final var password = customUserDto.password();
			final var newCustomUser = new CustomUser();
			newCustomUser.setUsername(username);
			newCustomUser.setPassword(password);
			this.customUserRepository.save(newCustomUser);
			return "User saved.";
		}
		
	}
	
	String addUser(CustomUserDto customUserDto);
}
