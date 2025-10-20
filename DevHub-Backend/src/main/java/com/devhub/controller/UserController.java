package com.devhub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devhub.dto.ApiResponse;
import com.devhub.dto.SignUpDto;
import com.devhub.entity.User;
import com.devhub.service_interface.UserDao;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api/user")
@CrossOrigin
@Tag(name = "User Controller", description = "User management APIs")
public class UserController { 

	@Autowired
	private UserDao userDao;
	
	@PostMapping("/register")
	public ResponseEntity<SignUpDto> registerUser(@Valid @RequestBody SignUpDto user){
		return ResponseEntity.status(HttpStatus.CREATED).body(userDao.registerUser(user));
	}
	
	
	@GetMapping("/id")
	public ResponseEntity<User> getUserById(@Valid Long userId){
		return ResponseEntity.ok(userDao.getUserById(userId));
	}
	
	@DeleteMapping("/id")
	public ResponseEntity<?> deleteUserById(@Valid Long userId){
		Boolean deleteUserById = userDao.deleteUserById(userId);
		if(deleteUserById) {
			return ResponseEntity.ok("User deleted");
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User Not Exist");
		
	}
	
	
}
