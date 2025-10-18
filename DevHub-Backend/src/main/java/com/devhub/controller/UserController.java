package com.devhub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@RequestMapping("/user")
@CrossOrigin
@Tag(name = "User Controller", description = "User management APIs")
public class UserController { 

	@Autowired
	private UserDao userDao;
	
	@PostMapping("/register")
	private ResponseEntity<SignUpDto> registerUser(@Valid @RequestBody SignUpDto user){
		return ResponseEntity.status(HttpStatus.CREATED).body(userDao.registerUser(user));
	}
	
}
