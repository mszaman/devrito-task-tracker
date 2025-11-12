package com.devrito.tasks.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devrito.tasks.domain.dto.TaskDto;
import com.devrito.tasks.domain.entities.Task;
import com.devrito.tasks.mappers.TaskMapper;
import com.devrito.tasks.services.TaskService;

@RestController
@RequestMapping(path = "/api/task-lists/{task_list_id}/tasks")
public class TaskController {

	private final TaskService taskService;
	private final TaskMapper taskMapper;

	public TaskController(TaskService taskService, TaskMapper taskMapper) {
		this.taskService = taskService;
		this.taskMapper = taskMapper;
	}

	@GetMapping
	public ResponseEntity<List<TaskDto>> listTasks(@PathVariable("task_list_id") UUID taskListId) {

		return new ResponseEntity<List<TaskDto>>(taskService.listTasks(taskListId).stream().map(taskMapper::toDto).toList(),
				HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<TaskDto> createTask(@PathVariable("task_list_id") UUID taskListId,
			@RequestBody TaskDto taskDto) {

		Task createdTask = taskService.createTask(
				taskListId,
				taskMapper.fromDto(taskDto));

		return new ResponseEntity<TaskDto>(
				taskMapper.toDto(createdTask),
				HttpStatus.CREATED);
	}
}
