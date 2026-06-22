package com.shrinivas.kafka_project_listener.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import com.shrinivas.kafka_core.ProductCreatedEvent;
import com.shrinivas.kafka_project_listener.Exceptions.NotRetryableException;
import com.shrinivas.kafka_project_listener.Exceptions.RetryableException;

@Component
@KafkaListener(topics="product-create-events-topic")
public class ProductCreatedEventHandler {

	private final Logger LOGGER =  LoggerFactory.getLogger(this.getClass());
	private RestTemplate restTemplate;
	
	public ProductCreatedEventHandler(RestTemplate restTemplate) {
		this.restTemplate=restTemplate;
	}
	
	@KafkaHandler
	public void handle(ProductCreatedEvent productCreatedEvent) {
//		if(true) throw new NotRetryableException("An error took place. No retry to consume this message again");
		LOGGER.info("Received a new event: " + productCreatedEvent.getName() + " with productId: "+productCreatedEvent.getProductId());
	
		String requestUrl = "http://localhost:8083/200";
		try {
			ResponseEntity<String> response =  restTemplate.exchange(requestUrl, HttpMethod.GET, null, String.class);
			
			if(response.getStatusCode().value() == HttpStatus.OK.value()) {
				LOGGER.info("This is response from requested URL: "+response.getBody());
			}
		} catch(ResourceAccessException ex){
			LOGGER.error(ex.getMessage());
			throw new RetryableException(ex);
		} catch(HttpServerErrorException ex) {
			LOGGER.error(ex.getMessage());
			throw new NotRetryableException(ex);
		} catch (Exception ex) {
			LOGGER.error(ex.getMessage());
			throw new NotRetryableException(ex);
		}
	}	
}
