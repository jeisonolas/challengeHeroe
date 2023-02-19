package com.in2.technical.challenge.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebSecurityConfig {

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		return http
				.csrf().disable()
				.authorizeRequests()
				.antMatchers(	"/v3/api-docs/**",
				        		"/swagger-ui/**",
				        		"/swagger-ui.html").permitAll()
				.anyRequest().authenticated()
				.and()
				.httpBasic()
				.and()
				.build();
	}
}
