package com.devhub.custome_exception;

import org.springframework.http.HttpStatus;

public class ResourceNotFoundException extends RuntimeException {

	public ResourceNotFoundException() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public ResourceNotFoundException(String resource, Long id, HttpStatus status) {
		super(resource +" not found with Id : "+id+ " "+status);
	}
}
