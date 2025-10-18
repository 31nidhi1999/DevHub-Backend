package com.devhub.dto;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SignInReqDto {
	
	@NotNull
	private String username;
	
	@NotNull
	@Length(max = 5, min = 20)
	private String password;
}
