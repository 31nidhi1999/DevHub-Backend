package com.devhub.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devhub.entity.Task;

@Repository		
public interface TaskRepository extends JpaRepository<Task, Long> {
	List<Task> findByProjectId(Long projectId);
	Optional<Task> findById(Long taskId);
	Task findByTitle(String title);
}
