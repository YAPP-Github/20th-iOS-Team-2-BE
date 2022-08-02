package com.yapp.api.global.web.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.yapp.api.global.security.auth.resolver.AuthenticationHasFamily;
import com.yapp.api.global.security.auth.resolver.AuthenticationHasFamilyArgumentResolver;
import com.yapp.api.global.security.auth.resolver.MustAuthenticatedArgumentResolver;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {
	private final MustAuthenticatedArgumentResolver mustAuthenticatedArgumentResolver;
	private final AuthenticationHasFamilyArgumentResolver authenticationHasFamilyArgumentResolver;

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
		resolvers.add(mustAuthenticatedArgumentResolver);
		resolvers.add(authenticationHasFamilyArgumentResolver);
	}

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
				.allowCredentials(false)
				.allowedOrigins("*")
				.allowedMethods("*")
				.allowedHeaders("*");
	}
}
