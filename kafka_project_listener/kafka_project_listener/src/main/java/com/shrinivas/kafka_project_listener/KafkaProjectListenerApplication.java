package com.shrinivas.kafka_project_listener;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class KafkaProjectListenerApplication {

	public static void main(String[] args) {
		SpringApplication.run(KafkaProjectListenerApplication.class, args);
	}
	
	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

}
