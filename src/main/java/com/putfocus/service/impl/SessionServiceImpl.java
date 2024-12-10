package com.putfocus.service.impl;

import com.putfocus.entities.Session;
import com.putfocus.entities.Task;
import com.putfocus.repository.SessionRepository;
import com.putfocus.repository.TaskRepository;
import com.putfocus.service.SessionService;
import org.modelmapper.ModelMapper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;

@Service
public class SessionServiceImpl implements SessionService {

    private final SessionRepository sessionRepository;
    private final TaskRepository taskRepository;
    private final LocalDateTime endTime = LocalDateTime.now().plusSeconds(10);
    private boolean timeUp = false;

    public SessionServiceImpl(SessionRepository sessionRepository, TaskRepository taskRepository, ModelMapper modelMapper) {
        this.sessionRepository = sessionRepository;
        this.taskRepository = taskRepository;
    }

    @Override
    public void saveSession(Long id, Session session) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));
        session.setTask(task);
        session.setStartTime(LocalDateTime.now());
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

//    @Scheduled(fixedRate = 1000)
//    public void countdown() {
//        LocalDateTime now = LocalDateTime.now();
//
//        if (!now.isBefore(endTime) && !timeUp) {
//            timeUp = true; // Marcar que o tempo acabou e a mensagem foi exibida
//            System.out.println("O tempo acabou!");
//            return; // Não fazer mais nada depois disso
//        }
//
//        Duration remainingDuration = Duration.between(now, endTime);
//
//        String formattedTime = formatTime(remainingDuration);
//
//        System.out.println("Tempo restante: " + formattedTime);
//    }
//
//    private String formatTime(Duration duration) {
//        long hours = duration.toHours();
//        long minutes = duration.toMinutes() % 60;
//        long seconds = duration.getSeconds() % 60;
//
//        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
//    }
}