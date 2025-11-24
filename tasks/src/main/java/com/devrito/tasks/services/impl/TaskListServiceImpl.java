package com.devrito.tasks.services.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.devrito.tasks.domain.entities.TaskList;
import com.devrito.tasks.exceptions.ResourceNotFoundException;
import com.devrito.tasks.exceptions.ValidationException;
import com.devrito.tasks.repositories.TaskListRepository;
import com.devrito.tasks.services.TaskListService;

import jakarta.transaction.Transactional;

@Service
public class TaskListServiceImpl implements TaskListService {

	private final TaskListRepository taskListRepository;

	public TaskListServiceImpl(TaskListRepository taskListRepository) {
		this.taskListRepository = taskListRepository;
	}

	@Override
	public List<TaskList> listTaskLists() {
		List<TaskList> taskLists = taskListRepository.findAll();

		// if (taskLists.isEmpty()) {
		// throw new ResourceNotFoundException("Task lists not found.");
		// }

		return taskLists;
	}

	@Override
	public TaskList createTaskList(TaskList taskList) {

		if (null != taskList.getId()) {
			throw new IllegalArgumentException("Task list already has an ID.");
		}

		if (null == taskList.getTitle() || taskList.getTitle().isBlank()) {
			throw new ValidationException("Task list must has a title.");
		}

		LocalDateTime now = LocalDateTime.now();

		return taskListRepository.save(
				new TaskList(null, taskList.getTitle(), taskList.getDescription(), null, now, now));
	}

	@Override
	public Optional<TaskList> getTaskList(UUID id) {
		Optional<TaskList> taskList = taskListRepository.findById(id);

		if (taskList.isEmpty()) {
			throw new ResourceNotFoundException("Task list not found.");
		}

		return taskList;
	}

	@Transactional
	@Override
	public TaskList updateTaskList(UUID taskListId, TaskList taskList) {

		if (null == taskList.getId()) {
			throw new IllegalArgumentException("Task list must have an ID.");
		}

		if (!Objects.equals(taskListId, taskList.getId())) {
			throw new IllegalArgumentException("Attempting to change task list ID. This is not permitted.");
		}

		TaskList existingTaskList = taskListRepository.findById(taskListId)
				.orElseThrow(() -> new ResourceNotFoundException("Task list not found"));

		existingTaskList.setTitle(taskList.getTitle());
		existingTaskList.setDescription(taskList.getDescription());
		existingTaskList.setUpdated(LocalDateTime.now());

		return taskListRepository.save(existingTaskList);
	}

	@Override
	public void deleteTaskList(UUID taskListId) {
		Optional<TaskList> taskListToDelete = taskListRepository.findById(taskListId);

		if (taskListToDelete.isEmpty()) {
			throw new ResourceNotFoundException("Task list not found.");
		}

		taskListRepository.deleteById(taskListId);
	}

}
