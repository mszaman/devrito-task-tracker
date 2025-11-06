package com.devrito.tasks.services.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.devrito.tasks.domain.entities.TaskList;
import com.devrito.tasks.repositories.TaskListRepository;
import com.devrito.tasks.services.TaskListService;

@Service
public class TaskListServiceImpl implements TaskListService {

	private final TaskListRepository taskListRepository;

	public TaskListServiceImpl(TaskListRepository taskListRepository) {
		this.taskListRepository = taskListRepository;
	}

	@Override
	public List<TaskList> listTaskLists() {
		return taskListRepository.findAll();
	}

	@Override
	public TaskList createTaskList(TaskList taskList) {

		if (null != taskList.getId()) {
			throw new IllegalArgumentException("Task list already has an ID.");
		}

		if (null == taskList.getTitle() || taskList.getTitle().isBlank()) {
			throw new IllegalArgumentException("Task list must has a title.");
		}

		LocalDateTime now = LocalDateTime.now();

		return taskListRepository.save(
				new TaskList(null, taskList.getTitle(), taskList.getDescription(), null, now, now));
	}

}
