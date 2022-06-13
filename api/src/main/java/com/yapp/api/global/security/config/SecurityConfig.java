package com.yapp.api.global.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
		return httpSecurity.formLogin(AbstractHttpConfigurer::disable)
						   .httpBasic(AbstractHttpConfigurer::disable)
						   .authorizeHttpRequests(request -> request.anyRequest()
																	.permitAll())
						   .build();
	}

	@Bean
	public WebSecurityCustomizer configure() {
		return WebSecurity::ignoring;
	}
}
