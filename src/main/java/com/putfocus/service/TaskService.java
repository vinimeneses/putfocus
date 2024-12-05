package com.putfocus.service;

import com.putfocus.dto.TaskDto;
import com.putfocus.entities.Task;

import java.util.List;

public interface TaskService {
    void createTask(TaskDto taskDto);
    void updateTask(TaskDto taskDto);
    void deleteTask(Long id);
    List<TaskDto> getAllTasks();
    Task findTaskById(Long id);
}
