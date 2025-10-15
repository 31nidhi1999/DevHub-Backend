package com.devhub.dto;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotNull;

public class ProjectReqDto {
	@NotNull
	@Length(max = 20,min = 10)
	private String name;
	
	@NotNull
	@Length(max=200, min=20)
	private String description;
}
