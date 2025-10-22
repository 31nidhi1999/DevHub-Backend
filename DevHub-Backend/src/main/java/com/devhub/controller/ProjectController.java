package com.devhub.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devhub.dto.ProjectReqDto;
import com.devhub.dto.ProjectResDto;
import com.devhub.entity.Project;
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
		System.out.println(project.getUserId());
		return ResponseEntity.ok(projectDao.createProject(project));
	}
	
	@GetMapping("/active")
	public ResponseEntity<List<ProjectResDto>> getActiveProject(){
		
		return ResponseEntity.ok(projectDao.getActiveProject());
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<ProjectResDto>> getAllProject() {

		return ResponseEntity.ok(projectDao.getAllProject());
	}
	
	@GetMapping("/id")
	public ResponseEntity<ProjectResDto>  getProjectById(@Valid Long projId){
		return ResponseEntity.ok(projectDao.getProjectById(projId));
	}
	
	@DeleteMapping("/id")
	public ResponseEntity<String> deteleProjectById(@Valid Long projId){
		Boolean result = projectDao.deleteProjectById(projId);
		if(result) {
			return ResponseEntity.ok("Project Deleted");
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Project Not Found");
		
	}
	
	@GetMapping("/userid")
	public ResponseEntity<List<ProjectResDto>> getAllProjectCreateByUserId(@Valid Long userId){
		return ResponseEntity.ok(projectDao.getAllProjectCreatedByUserId(userId));
	}
	
}
