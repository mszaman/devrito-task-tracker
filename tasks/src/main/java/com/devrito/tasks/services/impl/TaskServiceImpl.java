package com.devrito.tasks.services.impl;

import java.time.LocalDateTime;
import java.util.List;
<<<<<<< HEAD
=======
import java.util.Objects;
>>>>>>> refs/remotes/origin/main
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

<<<<<<< HEAD
=======
import jakarta.transaction.Transactional;

>>>>>>> refs/remotes/origin/main
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

<<<<<<< HEAD
=======
	@Transactional
>>>>>>> refs/remotes/origin/main
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

<<<<<<< HEAD
		Task taskToSave = new Task(null, task.getTitle(), task.getDescription(), now, taskStatus,
=======
		Task taskToSave = new Task(null, task.getTitle(), task.getDescription(), task.getDueDate(), taskStatus,
>>>>>>> refs/remotes/origin/main
				taskPriority, taskList, now, now);

		return taskRepository.save(taskToSave);
	}

<<<<<<< HEAD
=======
	@Transactional
	@Override
	public Optional<Task> getTask(UUID taskListId, UUID taskId) {

		Optional<TaskList> taskList = taskListRepository.findById(taskListId);

		if (taskList.isEmpty()) {
			throw new ResourceNotFoundException("Task list not found.");
		}

		Optional<Task> selectedTask = taskRepository.findByTaskListIdAndId(taskListId, taskId);

		if (selectedTask.isEmpty()) {
			throw new ResourceNotFoundException("Task not found.");
		}
		return selectedTask;
	}

	@Transactional
	@Override
	public Task updateTask(UUID taskListId, UUID taskId, Task task) {

		if (null == taskId) {
			throw new IllegalArgumentException("Task must have an ID.");
		}

		if (!Objects.equals(taskId, task.getId())) {
			throw new IllegalArgumentException("Attempting to change task ID. This is not permitted.");
		}

		if (null == task.getTitle() || task.getTitle().isBlank()) {
			throw new ValidationException("Task must have a title.");
		}

		if (null == task.getPriority()) {
			throw new ValidationException("Task must have a priority.");
		}

		Optional<TaskList> taskList = taskListRepository.findById(taskListId);

		if (taskList.isEmpty()) {
			throw new ResourceNotFoundException("Task list not found.");
		}

		Task existingTask = taskRepository.findByTaskListIdAndId(taskListId, taskId)
				.orElseThrow(() -> new ResourceNotFoundException("Task not found."));

		existingTask.setTitle(task.getTitle());
		existingTask.setDescription(task.getDescription());
		existingTask.setDueDate(task.getDueDate());
		existingTask.setPriority(task.getPriority());
		existingTask.setStatus(task.getStatus() != null ? task.getStatus() : existingTask.getStatus());
		existingTask.setUpdated(LocalDateTime.now());

		return taskRepository.save(existingTask);
	}

	@Transactional
	@Override
	public void deleteTask(UUID taskListId, UUID taskId) {

		Optional<TaskList> taskList = taskListRepository.findById(taskListId);

		if (taskList.isEmpty()) {
			throw new ResourceNotFoundException("Task list not found.");
		}

		Optional<Task> existingTask = taskRepository.findByTaskListIdAndId(taskListId, taskId);

		if (existingTask.isEmpty()) {
			throw new ResourceNotFoundException("Task not found.");
		}

		taskRepository.deleteByTaskListIdAndId(taskListId, taskId);
	}

>>>>>>> refs/remotes/origin/main
}
