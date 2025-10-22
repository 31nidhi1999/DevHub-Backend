package com.devhub.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devhub.dto.TaskReqDto;
import com.devhub.dto.TaskResDto;
import com.devhub.service_interface.TaskDao;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/tasks")
@CrossOrigin
public class TaskController {
	
	@Autowired
	private TaskDao taskDao;
	
	@PostMapping
	public ResponseEntity<TaskResDto> createTask(@Valid @RequestBody TaskReqDto taskDto){
		return ResponseEntity.ok(taskDao.createTask(taskDto));
	}
	
	@GetMapping
	public ResponseEntity<List<TaskResDto>> getAllTask(){
		return ResponseEntity.ok(taskDao.getAllTask());
	}
	
	@DeleteMapping("/{taskId}")
	public ResponseEntity<?> deleteTaskById(@Valid Long taskId){
		Boolean result = taskDao.deleteTaskById(taskId);
		if(result) {
			return ResponseEntity.ok("Task Deleted");
		}
		return ResponseEntity.ok("Task not deleted");
	}
	
	@GetMapping("/project/{projectId}")
	public ResponseEntity<List<TaskResDto>> getAllTaskUnderProjectId(@Valid Long projectId){
		return ResponseEntity.ok(taskDao.getAllTaskUnderProjectId(projectId));
		
	}
	
	@PatchMapping("/{taskId}/assign/{userId}")
	public ResponseEntity<TaskResDto> assignTaskToUserId(@Valid Long taskId, @Valid Long userId){
		return ResponseEntity.ok(taskDao.assignTaskToUserId(taskId, userId));
		
	} 
	
}
