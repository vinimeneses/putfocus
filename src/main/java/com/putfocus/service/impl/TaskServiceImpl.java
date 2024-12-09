package com.putfocus.service.impl;

import com.putfocus.dto.TaskDto;
import com.putfocus.entities.Session;
import com.putfocus.entities.Task;
import com.putfocus.repository.TaskRepository;
import com.putfocus.service.TaskService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final ModelMapper modelMapper;

    public TaskServiceImpl(TaskRepository taskRepository, ModelMapper modelMapper) {
        this.taskRepository = taskRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public TaskDto createTask(TaskDto taskDto) {
        return modelMapper.
                map(taskRepository.save(modelMapper
                        .map(taskDto, Task.class)), TaskDto.class);
    }

    @Override
    public void updateTask(TaskDto taskDto) {
        Task task = modelMapper.map(taskDto, Task.class);
        taskRepository.save(task);
    }

    @Override
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    @Override
    public List<TaskDto> getAllTasks() {
        return taskRepository.findAll().stream()
                .map(task -> modelMapper.map(task, TaskDto.class))
                .toList();
    }

    @Override
    public TaskDto findTaskById(Long id) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));
        return modelMapper.map(task, TaskDto.class);
    }

    @Override
    public TaskDto incrementSession(Long id, Session session) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));
        task.getSessions().add(session);
        session.setTask(task);
        Task updatedTask = taskRepository.save(task);
        return modelMapper.map(updatedTask, TaskDto.class);
    }
}