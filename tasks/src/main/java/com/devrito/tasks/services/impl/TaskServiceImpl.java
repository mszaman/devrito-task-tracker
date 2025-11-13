package com.devrito.tasks.services.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.devrito.tasks.domain.entities.Task;
import com.devrito.tasks.domain.entities.TaskList;
import com.devrito.tasks.domain.entities.TaskPriority;
import com.devrito.tasks.domain.entities.TaskStatus;
import com.devrito.tasks.exceptions.ResourceNotFoundException;
import com.devrito.tasks.exceptions.ValidationException;
import com.devrito.tasks.repositories.TaskListRepository;
import com.devrito.tasks.repositories.TaskRepository;
import com.devrito.tasks.services.TaskService;

@Service
public class TaskServiceImpl implements TaskService {

	private final TaskRepository taskRepository;
	private final TaskListRepository taskListRepository;

	public TaskServiceImpl(TaskRepository taskRepository, TaskListRepository taskListRepository) {
		this.taskRepository = taskRepository;
		this.taskListRepository = taskListRepository;
	}

	@Override
	public List<Task> listTasks(UUID taskListId) {

		return taskRepository.findByTaskListId(taskListId);
	}

	@Override
	public Task createTask(UUID taskListId, Task task) {
		if (null != task.getId()) {
			throw new IllegalArgumentException("The task already has an ID.");
		}

		if (null == task.getTitle() || task.getTitle().isEmpty()) {
			throw new ValidationException("The task must have an ID.");
		}

		TaskPriority taskPriority = Optional.ofNullable(task.getPriority()).orElse(TaskPriority.MEDIUM);

		TaskStatus taskStatus = TaskStatus.OPEN;

		LocalDateTime now = LocalDateTime.now();

		TaskList taskList = taskListRepository.findById(taskListId)
				.orElseThrow(() -> new ResourceNotFoundException("Task list not found."));

		Task taskToSave = new Task(null, task.getTitle(), task.getDescription(), task.getDueDate(), taskStatus,
				taskPriority, taskList, now, now);

		return taskRepository.save(taskToSave);
	}

}
