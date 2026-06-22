package com.shrinivas.kafka_project_listener.Exceptions;

public class NotRetryableException extends RuntimeException{

	public NotRetryableException(String message) {
		super(message);
	}

	public NotRetryableException(Throwable cause) {
		super(cause);
	}

	
	
}
