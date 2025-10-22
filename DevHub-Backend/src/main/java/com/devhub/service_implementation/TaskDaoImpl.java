
package com.devhub.service_implementation;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.devhub.custome_exception.ResourceNotFoundException;
import com.devhub.dto.TaskReqDto;
import com.devhub.dto.TaskResDto;
import com.devhub.entity.Project;
import com.devhub.entity.Task;
import com.devhub.entity.User;
import com.devhub.repo.ProjectRepository;
import com.devhub.repo.TaskRepository;
import com.devhub.repo.UserRepository;
import com.devhub.service_interface.TaskDao;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;


@Service
@Transactional
public class TaskDaoImpl implements TaskDao {
	
	@Autowired
	private TaskRepository taskRepo;
	
	@Autowired
	private ProjectRepository projectRepo;
	
	@Autowired
	private UserRepository  userRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public TaskResDto createTask(@Valid TaskReqDto taskDto) {
		System.out.println(taskDto.getProjectId());
		Project project = projectRepo.findById(taskDto.getProjectId()).orElseThrow(()-> new ResourceNotFoundException("Project",taskDto.getProjectId(),HttpStatus.NOT_FOUND));
		Task mapTask = modelMapper.map(taskDto, Task.class);
		mapTask.setProject(project);
		project.getTasks().add(mapTask);
		Task savedTask = taskRepo.save(mapTask);
		return modelMapper.map(savedTask, TaskResDto.class);
	}

	@Override
	public List<TaskResDto> getAllTask() {
		return taskRepo.findAll().stream().map(task->modelMapper.map(task, TaskResDto.class)).collect(Collectors.toList());
	}

	@Override
	public TaskResDto getTaskById(@Valid Long taskId) {
		Task task = taskRepo.findById(taskId).orElseThrow(()-> new ResourceNotFoundException("Task", taskId, HttpStatus.NOT_FOUND));
		return modelMapper.map(task, TaskResDto.class);
	}

	@Override
	public Boolean deleteTaskById(@Valid Long taskId) {
		
		return null;
	}

	@Override
	public List<TaskResDto> getAllTaskUnderProjectId(@Valid Long projId) {
		
		return taskRepo.findAll().stream().filter(task-> task.getProject().getId()==projId).map(task->modelMapper.map(task, TaskResDto.class)).collect(Collectors.toList());
	}

	@Override
	public TaskResDto assignTaskToUserId(@Valid Long taskId, @Valid Long userId) {
		Task task = taskRepo.findById(taskId).orElseThrow(()-> new ResourceNotFoundException("Task", taskId, HttpStatus.NOT_FOUND));
		User user = userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", userId, HttpStatus.NOT_FOUND));
		task.setUser(user);
		user.getTasks().add(task);
		Task savedTask = taskRepo.save(task);
		return modelMapper.map(savedTask, TaskResDto.class);
	}

}
