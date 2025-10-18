package com.devhub.dto;

public class SignInResDto {
	private String jwt;
	private String msg;
	
	public SignInResDto(String jwt, String msg) {
		super();
		this.jwt = jwt;
		this.msg = msg;
	}
	
	
}
