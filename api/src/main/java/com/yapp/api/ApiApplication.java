package com.yapp.api;

import static java.util.TimeZone.*;

import javax.annotation.PostConstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan("com.yapp.supporter")
@EnableJpaRepositories("com.yapp.**")
@EnableJpaAuditing
@SpringBootApplication(scanBasePackages = "com.yapp.**")
public class ApiApplication {

	@PostConstruct
	void started() {
		setDefault(getTimeZone("Asia/Seoul"));
	}

	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
	}

}
