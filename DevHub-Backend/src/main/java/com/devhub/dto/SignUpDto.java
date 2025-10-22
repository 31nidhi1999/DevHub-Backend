package com.devhub.dto;

import com.devhub.entity.UserRole;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class SignUpDto {
	
	@JsonProperty(access = Access.READ_ONLY)
	private Long id;

	@NotNull(message = "User Name required")
	private String username;
	
	@Email
	@NotNull(message = "Email required")
	private String email;
	
	@JsonProperty(access=Access.WRITE_ONLY)
	@NotBlank(message = "Password required")
	@Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[#@$*]).{5,20}$", 
    message = "Password must be 5-20 characters long, contain at least one digit, one lowercase letter, one uppercase letter, and one special character (#@$*).")

	private String password;
	
	private UserRole role;
	
}
