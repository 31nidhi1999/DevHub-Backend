package com.devhub.service_implementation;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.devhub.custome_exception.ResourceNotFoundException;
import com.devhub.dto.ProjectReqDto;
import com.devhub.dto.ProjectResDto;
import com.devhub.entity.Project;
import com.devhub.entity.User;
import com.devhub.repo.ProjectRepository;
import com.devhub.repo.UserRepository;
import com.devhub.service_interface.ProjectDao;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class ProjectDaoImpl implements ProjectDao {

	@Autowired
	private ProjectRepository projectRepo;
	
	@Autowired
	private UserRepository userRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public ProjectResDto createProject(@Valid ProjectReqDto project) {
		
		User user = userRepo.findById(project.getUserId()).orElseThrow(()-> new ResourceNotFoundException("User",project.getUserId(),HttpStatus.NOT_FOUND));
		
		Project mapProj = modelMapper.map(project, Project.class);
		System.out.println(project.getUserId());
		
		
		
		mapProj.setUser(user);
		user.getProjects().add(mapProj);
		Project savedProj = projectRepo.save(mapProj);
		log.debug("User entity saved: {}", savedProj);
		log.info("User registration successful for email: {}", savedProj.getTitle());
		return modelMapper.map(savedProj, ProjectResDto.class);
	}

	@Override
	public List<ProjectResDto> getActiveProject() {

		return projectRepo.findAll().stream().filter(proj -> proj.getActive() == true)
				.map(proj -> modelMapper.map(proj, ProjectResDto.class)).collect(Collectors.toList());
	}

	@Override
	public List<ProjectResDto> getAllProject() {
		List<ProjectResDto> projects = projectRepo.findAll().stream().map(proj -> modelMapper.map(proj, ProjectResDto.class))
				.collect(Collectors.toList());
		return projects;
	}

	@Override
	public ProjectResDto getProjectById(@Valid Long projId) {
		Project project = projectRepo.findById(projId)
				.orElseThrow(() -> new ResourceNotFoundException("Project", projId, HttpStatus.NOT_FOUND));
		return modelMapper.map(project, ProjectResDto.class);
	}

	@Override
	public Boolean deleteProjectById(@Valid Long projId) {
		return projectRepo.findById(projId).map(proj -> {
			if(!proj.getActive()) {
				log.info("Project with ID is already inactive ", projId);
				return false;
			}
			proj.setActive(false);
			 log.info("Porject with ID  has been deactivated successfully.", projId);
			projectRepo.save(proj);
			return true;
		}).orElseGet(()->{
			log.warn("Attempted to delete non-existing user with ID ", projId);
            return false;
		});
	}

	@Override
	public List<ProjectResDto> getAllProjectCreatedByUserId(@Valid Long userId) {
		
		return projectRepo.findAll().stream().filter(proj -> proj.getUser().getId() == userId)
				.map(proj -> modelMapper.map(proj, ProjectResDto.class)).collect(Collectors.toList());
	}

}
