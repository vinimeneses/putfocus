package com.putfocus.service;

import com.putfocus.dto.TaskDto;
import com.putfocus.entities.Session;

import java.util.List;

public interface TaskService {
    TaskDto createTask(TaskDto task);

    void updateTask(TaskDto task);

    void deleteTask(Long id);

    List<TaskDto> getAllTasks();

    TaskDto findTaskById(Long id);

    TaskDto incrementSession(Long id, Session session);
}