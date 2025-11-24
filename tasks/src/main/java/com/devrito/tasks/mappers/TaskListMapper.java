package com.devrito.tasks.mappers;

import com.devrito.tasks.domain.dto.TaskListDto;
import com.devrito.tasks.domain.entities.TaskList;

public interface TaskListMapper {
	TaskList fromDto(TaskListDto taskListDto);

	TaskListDto toDto(TaskList taskList);
}
