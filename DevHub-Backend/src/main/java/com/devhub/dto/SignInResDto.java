package com.devhub.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class SignInResDto {
	private String jwt;
	private String msg;
	
	public SignInResDto(String jwt, String msg) {
		super();
//		System.out.println("Incoming jwt "+ jwt);
		this.jwt = jwt;
		this.msg = msg;
//		System.out.println(this.jwt);
	}
	
	
}
