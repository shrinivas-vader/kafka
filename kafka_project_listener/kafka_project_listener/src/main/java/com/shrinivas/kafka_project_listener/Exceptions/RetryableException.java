package com.shrinivas.kafka_project_listener.Exceptions;

public class RetryableException extends RuntimeException{

	public RetryableException(String message) {
		super(message);
	}

	public RetryableException(Throwable cause) {
		super(cause);
	}

	
}
