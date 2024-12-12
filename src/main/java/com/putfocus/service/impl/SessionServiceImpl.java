package com.putfocus.service.impl;

import com.putfocus.entities.Session;
import com.putfocus.entities.Task;
import com.putfocus.repository.SessionRepository;
import com.putfocus.repository.TaskRepository;
import com.putfocus.service.SessionService;
import com.putfocus.util.Countdown;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class SessionServiceImpl implements SessionService {

    private final SessionRepository sessionRepository;
    private final TaskRepository taskRepository;

    public SessionServiceImpl(SessionRepository sessionRepository, TaskRepository taskRepository, ModelMapper modelMapper) {
        this.sessionRepository = sessionRepository;
        this.taskRepository = taskRepository;
    }

    @Override
    public void startSession(Long id, Session session) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));
        session.setTask(task);
        session.setStartTime(LocalDateTime.now());

        Countdown.startCountdown(session.getSessionDuration());
        session.setEndTime(LocalDateTime.now().plusMinutes(90));
        session.setActive(true);
        if (checkCompletedSessions(session)) {
            sessionRepository.save(session);
            }
    }

    public boolean checkCompletedSessions(Session session) {
        if (isSessionCompleted(session)) {
            markSessionAsCompleted(session);
            return true;
        } else {
            return false;
        }
    }

    private boolean isSessionCompleted(Session session) {
        return session.getEndTime().isBefore(LocalDateTime.now()) && session.isActive();
    }

    private void markSessionAsCompleted(Session session) {
        session.setCompleted(true);
        session.setActive(false);
        sessionRepository.save(session);
    }
}