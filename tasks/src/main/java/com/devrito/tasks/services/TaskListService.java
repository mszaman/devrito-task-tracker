package com.devrito.tasks.services;

import java.util.List;

import com.devrito.tasks.domain.entities.TaskList;

public interface TaskListService {
	List<TaskList> listTaskLists();

	TaskList createTaskList(TaskList taskList);
}
