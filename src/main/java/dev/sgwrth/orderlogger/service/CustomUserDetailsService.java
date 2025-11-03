package dev.sgwrth.orderlogger.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import dev.sgwrth.orderlogger.repository.CustomUserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	private final CustomUserRepository customUserRepository;
	
	CustomUserDetailsService(CustomUserRepository customUserRepository) {
		this.customUserRepository = customUserRepository;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException
	{
		return this.customUserRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User not found."));
	}

}
