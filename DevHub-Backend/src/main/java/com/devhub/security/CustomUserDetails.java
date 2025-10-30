package com.devhub.security;

import java.util.Collection;
import java.util.List;

import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.devhub.entity.User;

import io.jsonwebtoken.Claims;

public class CustomUserDetails implements UserDetails {
	
	@Autowired
	private User user;
	
	

	public CustomUserDetails(User persistentUser) {
		super();
		this.user = persistentUser;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return List.of( new SimpleGrantedAuthority(user.getRole().name()));
	}

	@Override
	public @Nullable String getPassword() {
		// TODO Auto-generated method stub
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return user.getUsername();
	}

	public User getUser() {
		// TODO Auto-generated method stub
		return user;
	}

}
