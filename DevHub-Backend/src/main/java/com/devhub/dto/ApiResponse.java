package com.devhub.dto;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@NoArgsConstructor
@Setter
@Getter
public class ApiResponse {
	private LocalDateTime timeStamp;
	private String message;
	private HttpStatus responseStatus;
	
	public ApiResponse(String message) {
		super();
		this.message = message;
		this.timeStamp=LocalDateTime.now();
	}
	public ApiResponse(String message, HttpStatus status) {
		super();
		this.message = message;
		this.timeStamp=LocalDateTime.now();
		responseStatus = status;
	}
}
