package com.devhub.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="users")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class User extends BaseEntity {
	
	@Column(unique = true,nullable = false)
	private String username;
	@Column(unique = true,nullable = false)
    private String email;
	@Column(nullable = false)
    private String password;
	
	@Enumerated(EnumType.STRING )
	private UserRole role;
}
