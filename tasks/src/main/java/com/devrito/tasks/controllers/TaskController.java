package com.devrito.tasks.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devrito.tasks.domain.dto.TaskDto;
import com.devrito.tasks.mappers.TaskMapper;
import com.devrito.tasks.services.TaskService;

@RestController
@CrossOrigin
@RequestMapping(path = "/api/task-lists/{task_list_id}/tasks")
public class TaskController {
	private final TaskService taskService;
	private final TaskMapper taskMapper;

	public TaskController(TaskService taskService, TaskMapper taskMapper) {
		this.taskService = taskService;
		this.taskMapper = taskMapper;
	}

	public ResponseEntity<List<TaskDto>> listTasks(@PathVariable UUID task_list_id) {

		return null;
	}

}
