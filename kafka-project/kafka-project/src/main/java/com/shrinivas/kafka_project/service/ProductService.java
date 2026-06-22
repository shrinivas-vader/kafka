package com.shrinivas.kafka_project.service;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import com.shrinivas.kafka_core.ProductCreatedEvent;
import com.shrinivas.kafka_project.model.Product;

@Service
public class ProductService {

	private KafkaTemplate<String, ProductCreatedEvent> kafkaTemplate;
	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	
	public ProductService(KafkaTemplate kafkaTemplate) {
		this.kafkaTemplate=kafkaTemplate;
	}
	
	public String createProduct(Product productDetails) throws Exception {
		String productId = UUID.randomUUID().toString();
		ProductCreatedEvent productCreatedEvent = new ProductCreatedEvent(productId,
				productDetails.getName(),
				productDetails.getPrice()
				);
		
		SendResult<String, ProductCreatedEvent> result = 
				kafkaTemplate.send("product-create-events-topic", productId, productCreatedEvent).get();
		
		LOGGER.info("Partition: "+result.getRecordMetadata().partition());
		LOGGER.info("Topic: "+result.getRecordMetadata().topic());
		LOGGER.info("Offset: "+result.getRecordMetadata().offset());
		LOGGER.info("DATA: "+result);
				/*
				 * //Asynchronus message sending CompletableFuture<SendResult<String,
				 * ProductCreatedEvent>> future =
				 * kafkaTemplate.send("product-create-events-topic", productId,
				 * productCreatedEvent); future.whenComplete((result, exception) -> {
				 * if(exception != null) { LOGGER.error("Faied to send message: " +
				 * exception.getMessage()); }else {
				 * LOGGER.info("Message sent successfully: "+result.getRecordMetadata()); } });
				 */
		return productId;
	}
	
}
