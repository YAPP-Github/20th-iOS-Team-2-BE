package com.yapp.api.global.web.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.yapp.api.global.security.auth.resolver.AuthenticationHasFamilyArgumentResolver;
import com.yapp.api.global.security.auth.resolver.MustAuthenticatedArgumentResolver;

@Configuration
public class WebConfig implements WebMvcConfigurer {
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
		resolvers.add(new MustAuthenticatedArgumentResolver());
		resolvers.add(new AuthenticationHasFamilyArgumentResolver());
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
