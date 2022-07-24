package com.yapp.realtime.kafka.config;

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
	private static final String BOOT_STRAP_SERVER = "localhost:9092";

}
