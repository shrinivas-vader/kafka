package com.shrinivas.mockServiceForStatusCodes.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StatusController {

	@GetMapping("/200")
	public ResponseEntity<String> Get200Controller(){
		return ResponseEntity.status(200).body("200 Status Code");
	}
	
	@GetMapping("/401")
	public ResponseEntity<String> Get401Controller(){
		return ResponseEntity.status(401).body("401 Status Code");
	}
	
	@GetMapping("/500")
	public ResponseEntity<String> Get500Controller(){
		System.out.println("Thsi is from 500 controller");
		return ResponseEntity.status(500).body("500 Status Code");
	}
	
	@GetMapping("/503")
	public ResponseEntity<String> Get503Controller(){
		return ResponseEntity.status(503).body("503 Status Code");
	}
}
