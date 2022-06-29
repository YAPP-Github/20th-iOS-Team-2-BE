package com.yapp.api.global.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.yapp.api.global.security.auth.bearer.filter.JwtAuthenticationFilter;
import com.yapp.api.global.security.auth.bearer.filter.JwtExceptionFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
	private final JwtAuthenticationFilter jwtAuthenticationFilter;
	private final JwtExceptionFilter jwtExceptionFilter;

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
		return httpSecurity.csrf(AbstractHttpConfigurer::disable)
						   .formLogin(AbstractHttpConfigurer::disable)
						   .httpBasic(AbstractHttpConfigurer::disable)
						   .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
						   // for h2-console, allow iframe
						   .headers(httpSecurityHeadersConfigurer -> httpSecurityHeadersConfigurer.frameOptions()
																								  .disable())
						   .oauth2Login(AbstractHttpConfigurer::disable)
						   .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
						   .addFilterBefore(jwtExceptionFilter, JwtAuthenticationFilter.class)
						   .authorizeRequests(authorizeRequestsCusomizer -> authorizeRequestsCusomizer.mvcMatchers(
																										  "/h2-console/**")
																									  .permitAll())
						   .build();
	}

	@Bean
	public WebSecurityCustomizer configure() {
		return (web) -> web.ignoring()
						   .antMatchers("/h2-console");
	}
}
