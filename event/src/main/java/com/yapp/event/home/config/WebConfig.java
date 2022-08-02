package com.yapp.event.home.config;

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Author : daehwan2yo
 * Date : 2022/08/02
 * Info : 
 **/
public class WebConfig implements WebMvcConfigurer {

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
				.allowCredentials(false)
				.allowedOrigins("*")
				.allowedMethods("*")
				.allowedHeaders("*");
	}
}
