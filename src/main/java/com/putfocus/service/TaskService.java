package com.putfocus.service;

import com.putfocus.entities.Task;

import java.util.List;

public interface TaskService {
    void createTask(Task task);

    void updateTask(Task task);

    void deleteTask(Long id);

    List<Task> getAllTasks();

    Task findTaskById(Long id);

    void incrementSession(Long taskId);
}