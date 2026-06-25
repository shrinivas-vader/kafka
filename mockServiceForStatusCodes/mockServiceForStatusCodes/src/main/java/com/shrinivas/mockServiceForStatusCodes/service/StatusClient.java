package com.shrinivas.mockServiceForStatusCodes.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
public class StatusClient {

	RestTemplate restTemplate = new RestTemplate();
	
	String url = "http://localhost:8080/500";
	
	@CircuitBreaker(name = "statusService", fallbackMethod = "fallbackMethod")
	public String callStatus() {
		
		return restTemplate.getForObject(url, String.class);
	}
	
	public String fallbackMethod(Throwable ex) {
	    System.out.println("Fallback called: " + ex.getClass().getName());
	    return "Status service is down broooo!!!!";
	}
			
			
}
