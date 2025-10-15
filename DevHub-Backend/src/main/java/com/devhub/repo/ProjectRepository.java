package com.devhub.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devhub.entity.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
}
