package com.devrito.tasks.services;

import java.util.List;
import java.util.UUID;

import com.devrito.tasks.domain.entities.Task;

public interface TaskService {
	List<Task> listTasks(UUID taskListId);

	Task createTask(UUID taskListId, Task task);
}
