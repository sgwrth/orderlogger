package dev.sgwrth.orderlogger.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import dev.sgwrth.orderlogger.filter.JwtAuthFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	private final JwtAuthFilter jwtAuthFilter;

	public SecurityConfig(JwtAuthFilter jwtAuthFilter) {
		this.jwtAuthFilter = jwtAuthFilter;
	}

	@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .cors(cors -> cors.disable())                 // we’ll handle it the easy way
            .sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/auth/**", "/log", "/order/new", "/ws/kafka",
                                 "/graphql/**", "/graphiql/**", "/vendor/**").permitAll()
                .anyRequest().authenticated()
            )
            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    // This single bean fixes CORS for EVERYTHING (Postman, browser, whatever)
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOriginPatterns(List.of("*"));
        config.setAllowedMethods(List.of("*"));
        config.setAllowedHeaders(List.of("*"));
        config.setAllowCredentials(false);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }

// 	@Bean
// 	@Order(1)
// 	public SecurityFilterChain graphQlSecurityFilterChain(HttpSecurity http)
// 			throws Exception
// 	{
// 		http
// 			.securityMatcher("/graphql/**", "/graphiql/**", "/vendor/**")
// 			.csrf(csrf -> csrf.disable())
// 			.authorizeHttpRequests(auth -> auth
// 				.anyRequest().permitAll()
// 			)
// 			.sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

//     return http.build();
// }

// 	@Bean
// 	@Order(2)
// 	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
// 		http
// 			.csrf(csrf -> csrf.disable())
// 			.authorizeHttpRequests(
// 					(authorizeHttpRequests) -> {
// 						authorizeHttpRequests
// 							.requestMatchers(
// 									"/auth/**",
// 									"/log",
// 									"/order/new",
// 									"/ws/kafka"
// 							).permitAll()
// 							.anyRequest().authenticated();
// 					}
// 			)
// 			.sessionManagement(
// 					(session) -> {
// 						session.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
// 					}
// 			)
// 			.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
// 			// .formLogin(Customizer.withDefaults())
// 			// .httpBasic(Customizer.withDefaults());
// 		return http.build();
// 	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration config)
			throws Exception
	{
		return config.getAuthenticationManager();
	}

}
