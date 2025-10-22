package com.devhub.dto;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@NoArgsConstructor
public class TaskReqDto {
	
	@JsonProperty(access = Access.READ_ONLY)
	private Long id;
	
	@NotNull
	@Length(max = 30,min = 10)
	private String title;
	
	@NotNull
	@Length(max=200, min=20)
    private String description;
	
	private Long projectId;
	
}
