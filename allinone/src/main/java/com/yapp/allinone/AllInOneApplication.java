package com.yapp.allinone;

import com.yapp.supporter.SupporterLocation;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(
        scanBasePackageClasses = {
                AllInOneLocation.class, SupporterLocation.class
        })
@EntityScan(basePackageClasses = SupporterLocation.class)
@EnableJpaRepositories(basePackageClasses = AllInOneLocation.class)
@EnableJpaAuditing
public class AllInOneApplication {

    public static void main(String[] args) {
        SpringApplication.run(AllInOneApplication.class, args);
    }

}
