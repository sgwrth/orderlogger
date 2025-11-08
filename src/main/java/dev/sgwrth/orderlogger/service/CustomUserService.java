package dev.sgwrth.orderlogger.service;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import dev.sgwrth.orderlogger.dto.CustomUserDto;
import dev.sgwrth.orderlogger.entity.CustomUser;
import dev.sgwrth.orderlogger.repository.CustomUserRepository;

public interface CustomUserService {

	@Service
	class CustomUserServiceImpl implements CustomUserService {
		
		private final CustomUserRepository customUserRepository;
		private final PasswordEncoder passwordEncoder;
		
		public CustomUserServiceImpl(
				CustomUserRepository customUserRepository,
				PasswordEncoder passwordEncoder
		) {
			this.customUserRepository = customUserRepository;
			this.passwordEncoder = passwordEncoder;
		}

		@Override
		public String addUser(CustomUserDto customUserDto) {
			customUserDto.setPassword(passwordEncoder.encode(customUserDto.getPassword()));
			var user = new CustomUser(customUserDto.getUsername(), customUserDto.getPassword());
			this.customUserRepository.save(user);
			return "User added.";
		}
		
		@Override
		public UserDetails loadByUsername(String username) throws UsernameNotFoundException {
			Optional<CustomUser> customUser
				= this.customUserRepository.findByUsername(username);
			if (customUser.isEmpty()) {
				throw new UsernameNotFoundException("Username not found");
			}
			return customUser.get();
		}

	}
	
	String addUser(CustomUserDto customUserDto);
	
	UserDetails loadByUsername(String username);

}
