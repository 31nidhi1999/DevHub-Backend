package com.devhub.service_implementation;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.devhub.custome_exception.ResourceNotFoundException;
import com.devhub.dto.ApiResponse;
import com.devhub.dto.SignUpDto;
import com.devhub.entity.User;
import com.devhub.repo.UserRepository;
import com.devhub.service_interface.UserDao;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class UserDaoImpl implements UserDao {
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public List<User> getAllUser() {
		return  userRepo.findAll()
				.stream()
				.map(user->modelMapper.map(user,User.class))
				.collect(Collectors.toList());
	}

	@Override
	public User getUserById(Long userId) {
		return userRepo.findById(userId).orElseThrow(() -> new  ResourceNotFoundException("User",userId,HttpStatus.NOT_FOUND));
	}

	@Override
	public SignUpDto registerUser(SignUpDto user) {
		User registerUser = modelMapper.map(user, User.class);
	
		User saveUser = userRepo.save(registerUser);
		
		return modelMapper.map(saveUser, SignUpDto.class);
	}
	

	
	

}
