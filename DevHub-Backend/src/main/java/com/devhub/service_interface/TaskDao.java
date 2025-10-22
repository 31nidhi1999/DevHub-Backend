package com.devhub.service_interface;

import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;

import com.devhub.dto.TaskReqDto;
import com.devhub.dto.TaskResDto;

import jakarta.validation.Valid;

public interface TaskDao {
	TaskResDto createTask(@Valid @RequestBody TaskReqDto taskDto);
	List<TaskResDto> getAllTask();
	TaskResDto getTaskById(@Valid Long taskId);
	Boolean deleteTaskById(@Valid Long taskId);
	List<TaskResDto> getAllTaskUnderProjectId(@Valid Long projId);
	TaskResDto assignTaskToUserId(@Valid Long taskId, @Valid Long userId);
	
}
