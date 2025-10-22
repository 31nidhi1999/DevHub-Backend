package com.devhub.service_interface;

import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;

import com.devhub.dto.ProjectReqDto;
import com.devhub.dto.ProjectResDto;
import com.devhub.entity.Project;

import jakarta.validation.Valid;

public interface ProjectDao {
	ProjectResDto createProject(@Valid @RequestBody ProjectReqDto project);
	List<ProjectResDto> getActiveProject();
	List<ProjectResDto> getAllProject();
	ProjectResDto getProjectById(@Valid @RequestBody Long projId);
	Boolean deleteProjectById(@Valid Long projId);
	List<ProjectResDto> getAllProjectCreatedByUserId(@Valid Long userId);
	
}
