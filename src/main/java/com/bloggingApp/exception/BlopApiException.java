package com.bloggingApp.exception;

import org.springframework.http.HttpStatus;

public class BlopApiException extends RuntimeException{
	
	private HttpStatus status;
	private String message;
	public HttpStatus getStatus() {
		return status;
	}
	public BlopApiException(HttpStatus status, String message) {
		super(message);
		this.status = status;
		this.message = message;
	}
	public String getMessage() {
		return message;
	}
	public HttpStatus setStatus(HttpStatus status) {
		return  status;
	}
	
	
	

}
