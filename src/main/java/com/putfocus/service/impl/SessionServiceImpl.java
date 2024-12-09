package com.putfocus.service.impl;

import com.putfocus.entities.Session;
import com.putfocus.entities.Task;
import com.putfocus.repository.SessionRepository;
import com.putfocus.repository.TaskRepository;
import com.putfocus.service.SessionService;
import org.modelmapper.ModelMapper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SessionServiceImpl implements SessionService {

    SessionRepository sessionRepository;
    TaskRepository taskRepository;
    ModelMapper modelMapper;

    public SessionServiceImpl(SessionRepository sessionRepository, TaskRepository taskRepository, ModelMapper modelMapper) {
        this.sessionRepository = sessionRepository;
        this.taskRepository = taskRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public void startSession(Long id, Session session) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));
        session.setTask(task);
        session.setStartTime(LocalDateTime.now());
        session.setEndTime(LocalDateTime.now().plusMinutes(90));
        session.setActive(true);
        sessionRepository.save(session);
    }


    @Scheduled(fixedRate = 1000)
    public void checkAndSaveCompletedSessions() {
        List<Session> activeSessions = sessionRepository.findAll().stream()
                .filter(Session::isActive)
                .toList();

        for (Session session : activeSessions) {
            if (LocalDateTime.now().isAfter(session.getEndTime())) {
                session.setCompleted(true);
                session.setActive(false);
                sessionRepository.save(session);
            }
        }
    }
}
