package com.yapp.event;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan("com.yapp.core")
@EnableJpaRepositories("com.yapp.core")
@SpringBootApplication(scanBasePackages = {"com.yapp.event", "com.yapp.core"})
public class EventApplication {

	public static void main(String[] args) {
		SpringApplication.run(EventApplication.class, args);
	}

}
