package com.devhub.dto;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotNull;

public class TastReqDto {
	@NotNull
	@Length(max = 20,min = 10)
	private String title;
	
	@NotNull
	@Length(max=200, min=20)
    private String description;
	
    private boolean completed;	
}
