package com.devhub.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devhub.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	User findByUsername(Long userId);
}
