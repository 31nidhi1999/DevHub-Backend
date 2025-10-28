package com.devhub.custome_exception;

public class ApiException extends RuntimeException {
	public ApiException(String msg) {
		super(msg);
	}
}
