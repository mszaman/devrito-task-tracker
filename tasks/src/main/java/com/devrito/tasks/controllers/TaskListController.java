package com.devrito.tasks.controllers;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devrito.tasks.domain.dto.TaskListDto;
import com.devrito.tasks.domain.entities.TaskList;
import com.devrito.tasks.mappers.TaskListMapper;
import com.devrito.tasks.services.TaskListService;

@RestController
@CrossOrigin
@RequestMapping(path = "/api/task-lists")
public class TaskListController {

	private final TaskListService taskListService;
	private final TaskListMapper taskListMapper;

	public TaskListController(TaskListService taskListService, TaskListMapper taskListMapper) {
		this.taskListService = taskListService;
		this.taskListMapper = taskListMapper;
	}

	@GetMapping
	public ResponseEntity<List<TaskListDto>> listTaskLists() {
		List<TaskListDto> taskListDto = taskListService.listTaskLists()
				.stream()
				.map(taskListMapper::toDto)
				.toList();

		return new ResponseEntity<>(taskListDto, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<TaskListDto> createTaskList(@RequestBody TaskListDto taskListDto) {
		System.out.println(taskListDto);
		TaskList createdTaskList = taskListService.createTaskList(
				taskListMapper.fromDto(taskListDto));

		return new ResponseEntity<TaskListDto>(taskListMapper.toDto(createdTaskList), HttpStatus.CREATED);
	}

	@GetMapping(path = "/{task_list_id}")
	public ResponseEntity<Optional<TaskListDto>> getTaskList(@PathVariable("task_list_id") UUID taskListId) {

		return new ResponseEntity<Optional<TaskListDto>>(taskListService.getTaskList(taskListId).map(taskListMapper::toDto),
				HttpStatus.OK);
	}

	@PutMapping(path = "/{task_list_id}")
	public ResponseEntity<TaskListDto> updateTaskList(
			@PathVariable("task_list_id") UUID taskListId,
			@RequestBody TaskListDto taskListDto) {

		TaskList updatedTaskList = taskListService.updateTaskList(taskListId, taskListMapper.fromDto(taskListDto));

		return new ResponseEntity<TaskListDto>(taskListMapper.toDto(updatedTaskList), HttpStatus.NO_CONTENT);
	}

	@DeleteMapping(path = "/{task_list_id}")
	public ResponseEntity<Void> deleteTaskList(@PathVariable("task_list_id") UUID taskListId) {

		taskListService.deleteTaskList(taskListId);

		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
