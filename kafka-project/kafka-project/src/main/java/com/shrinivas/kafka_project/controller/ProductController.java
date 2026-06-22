package com.shrinivas.kafka_project.controller;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.shrinivas.kafka_project.exceptions.ErrorMessage;
import com.shrinivas.kafka_project.model.Product;
import com.shrinivas.kafka_project.service.ProductService;

@RestController
public class ProductController {

	private ProductService productService;
	
	public ProductController(ProductService productService) {
		this.productService=productService;
	}
	
	@PostMapping("/product")
	public ResponseEntity<Object> createProduct(@RequestBody Product productDetails){
		String productId;
		try {
			productId = productService.createProduct(productDetails);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorMessage(new Date(),e.getMessage(),"/products"));
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(productId);
	}
}
