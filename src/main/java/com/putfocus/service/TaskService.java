package com.putfocus.service;

import com.putfocus.dto.TaskDto;
import java.util.List;

public interface TaskService {
    TaskDto createTask(TaskDto task);

    void updateTask(TaskDto task);

    void deleteTask(Long id);

    List<TaskDto> getAllTasks();

    TaskDto findTaskById(Long id);

    TaskDto incrementSession(Long taskId);
}