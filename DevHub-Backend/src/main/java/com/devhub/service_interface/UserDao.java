package com.devhub.service_interface;

import java.util.List;

import com.devhub.dto.ApiResponse;
import com.devhub.dto.SignUpDto;
import com.devhub.entity.User;

import jakarta.validation.Valid;

public interface UserDao {
	List<User> getAllUser();
	User getUserById( @Valid Long userId);
	SignUpDto registerUser(@Valid SignUpDto user);
	Boolean deleteUserById(@Valid Long userId);
}
