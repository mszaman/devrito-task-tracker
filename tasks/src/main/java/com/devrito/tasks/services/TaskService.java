package com.devrito.tasks.services;

import java.util.List;
<<<<<<< HEAD
=======
import java.util.Optional;
>>>>>>> refs/remotes/origin/main
import java.util.UUID;

import com.devrito.tasks.domain.entities.Task;

public interface TaskService {
	List<Task> listTasks(UUID taskListId);

	Task createTask(UUID taskListId, Task task);
<<<<<<< HEAD
=======

	Optional<Task> getTask(UUID taskListId, UUID taskId);

	Task updateTask(UUID taskListId, UUID taskId, Task task);

	void deleteTask(UUID taskListId, UUID taskId);
>>>>>>> refs/remotes/origin/main
}
