package com.devhub.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class RegisterReqDto {
	
	@JsonProperty(access = Access.READ_ONLY)
	private Long id;
	@NotNull
	private String username;
	
	@Email(message = "Please Enter a Valid Email")
	@NotNull
	private String email;
	
	
	@JsonProperty(access = Access.WRITE_ONLY)
	@Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[#@$*]).{5,20}$", 
		     message = "Password must be 5-20 characters long, contain at least one digit, one lowercase letter, one uppercase letter, and one special character (#@$*).")
	private String password;
	
}
