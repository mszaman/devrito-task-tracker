package com.devrito.tasks.mappers;

import com.devrito.tasks.domain.dto.TaskDto;
import com.devrito.tasks.domain.entities.Task;

public interface TaskMapper {
	Task fromDto(TaskDto taskDto);

	TaskDto toDto(Task task);
}
