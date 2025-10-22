package com.devhub.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class ProjectResDto {
	private Long id;
	private String title;
	private String description;
	private Long userId;
	private Boolean active;
	private Long projectId;

}
