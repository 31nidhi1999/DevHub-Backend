package com.devhub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devhub.dto.ProjectReqDto;
import com.devhub.dto.ProjectResDto;
import com.devhub.service_interface.ProjectDao;

import jakarta.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping("api/projects")
public class ProjectController {
	
	@Autowired
	private ProjectDao projectDao;
	
	@PostMapping("/create")
	public ResponseEntity<ProjectResDto> createProject(@Valid @RequestBody ProjectReqDto project){
		return ResponseEntity.ok(projectDao.createProject(project));
	}
	
	
}
