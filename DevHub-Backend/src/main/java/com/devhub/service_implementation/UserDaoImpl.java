package com.devhub.service_implementation;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.hibernate.validator.internal.util.logging.LoggerFactory;
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
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class UserDaoImpl implements UserDao {
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	


	@Override
	public List<User> getAllUser() {
		log.info("Fteching all user");
		return  userRepo.findAll()
				.stream()
				.map(user->modelMapper.map(user,User.class))
				.collect(Collectors.toList());
	}

	@Override
	public User getUserById(Long userId) {
		log.info("Fetchig user By userId ",userId);
		User user=userRepo.findById(userId).orElseThrow(()->{
			log.error("User not found with ID: {}", userId);
			return new ResourceNotFoundException("User", userId, HttpStatus.NOT_FOUND);
		});
		log.debug("User found: ", user.getUsername());
		return user;
	}

	@Override
	public SignUpDto registerUser(SignUpDto user) {
		User registerUser = modelMapper.map(user, User.class);
		User savedUser = userRepo.save(registerUser);
		  log.debug("User entity saved: {}", savedUser);
          log.info("User registration successful for email: {}", savedUser.getEmail());
		return modelMapper.map(savedUser, SignUpDto.class);
	}

	@Override
	public Boolean deleteUserById(@Valid Long userId) {
		if(userRepo.existsById(userId)) {
			User user = getUserById(userId);
			user.setActive(false);
			userRepo.save(user);
			log.info("User with ID  has been deactivated successfully.", userId);
			return true;
		}
		log.info("Attempted to delete non-existing user with ID ",userId);
		return false;
	}
	

	
	

}
