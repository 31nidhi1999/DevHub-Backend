package com.devhub.service_implementation;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import com.devhub.custome_exception.ResourceNotFoundException;
import com.devhub.dto.ProjectReqDto;
import com.devhub.dto.ProjectResDto;
import com.devhub.entity.Project;
import com.devhub.repo.ProjectRepository;
import com.devhub.service_interface.ProjectDao;

import jakarta.validation.Valid;

public class ProjectDaoImpl implements ProjectDao {
	
	@Autowired
	private ProjectRepository projectRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public ProjectResDto createProject(@Valid ProjectReqDto project) {
		Project mapProj = modelMapper.map(project, Project.class);
		Project saveProj = projectRepo.save(mapProj);
		return modelMapper.map(saveProj, ProjectResDto.class);
	}

	@Override
	public List<Project> getActiveProject() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Project> getAllProject() {
		List<Project> projects = projectRepo.findAll();
		return projects;
	}

	@Override
	public Project getProjectById(@Valid Long projId) {
		Project project = projectRepo.findById(projId).orElseThrow(()-> new ResourceNotFoundException("Project", projId, HttpStatus.NOT_FOUND));
		return project;
	}

	@Override
	public Boolean deleteProjectById(@Valid Long projId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Project> getAllProjectCreatedByUserId(@Valid Long userId) {
		// TODO Auto-generated method stub
		return null;
	}

}
