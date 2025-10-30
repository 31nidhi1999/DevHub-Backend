package com.devhub.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
public class SecurityConfig {
	
	@Autowired
	private CustomJwtAuthenticationFilter custJwtFilter;
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception{
		return config.getAuthenticationManager();
	}
	
	@Bean
	public SecurityFilterChain authorizeRequests(HttpSecurity http) throws Exception {
	    http.csrf(csrf -> csrf.disable())
	        .authorizeHttpRequests(auth -> auth
	            .requestMatchers(
	                "/users/signup",
	                "/users/login",
	                "/v3/api-docs/**",
	                "/v2/api-docs/**",
	                "/swagger-ui/**",
	                "/swagger-ui.html",
	                "/swagger-resources/**",
	                "/webjars/**",
	                "/api/user/register",
	                "/api/user/login"
	            ).permitAll()
	            
	            .requestMatchers(HttpMethod.OPTIONS).permitAll()
	            .requestMatchers("/api/projects/create").hasAuthority("ADMIN")
	            .anyRequest().authenticated()
	        )
	        .formLogin(form -> form.disable())
	        .httpBasic(basic -> basic.disable())
	        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
	        .addFilterBefore(custJwtFilter, UsernamePasswordAuthenticationFilter.class);

	    return http.build();
	}
	
}
