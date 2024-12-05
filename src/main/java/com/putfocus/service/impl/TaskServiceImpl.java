package com.putfocus.service.impl;

import com.putfocus.dto.TaskDto;
import com.putfocus.entities.Task;
import com.putfocus.repository.TaskRepository;
import com.putfocus.service.TaskService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Stream;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private ModelMapper modelMapper;

    public TaskServiceImpl(TaskRepository taskRepository, ModelMapper modelMapper) {
        this.taskRepository = taskRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public void createTask(TaskDto taskDto) {
        Task task = modelMapper.map(taskDto, Task.class);
        taskRepository.save(task);
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
        return Stream.of(taskRepository.findAll())
                .map(task -> modelMapper.map(task, TaskDto.class))
                .toList();
    }

    @Override
    public Task findTaskById(Long id) {
        return taskRepository.findTaskById(id);
    }
}
