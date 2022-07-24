package com.yapp.realtime.kafka.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;

/**
 * Author : daehwan2yo
 * Date : 2022/07/23
 * Info : 
 **/
@Configuration
@EnableKafka
public class KafkaConfig {
	@Value("${spring.kafka.bootstrap-servers}")
	private String BOOT_STRAP_SERVER;

}
