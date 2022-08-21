package com.yapp.realtime;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.RepositoryDefinition;

@EntityScan("com.yapp.supporter")
@EnableJpaRepositories("com.yapp.realtime")
@SpringBootApplication(scanBasePackages = {"com.yapp.supporter", "com.yapp.realtime"})
public class RealtimeApplication {

    public static void main(String[] args) {
        SpringApplication.run(RealtimeApplication.class, args);
    }

}
