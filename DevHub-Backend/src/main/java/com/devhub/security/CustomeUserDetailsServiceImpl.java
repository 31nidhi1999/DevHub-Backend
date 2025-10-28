package com.devhub.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.devhub.entity.User;
import com.devhub.repo.UserRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class CustomeUserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User persistentUser = userRepository.findByUsername(username).orElseThrow(()-> new UsernameNotFoundException("invalid user"));
		return new CustomUserDetails(persistentUser);
	}

}
