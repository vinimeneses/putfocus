package com.putfocus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.putfocus.entities.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {
    Task findTaskById(Long id);
}
