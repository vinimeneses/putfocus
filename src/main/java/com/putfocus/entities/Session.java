// Session.java
package com.putfocus.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Session {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "task_id", nullable = false)
    private Task task;

    private int sessionDuration = 90;
    private boolean completed = false;
    private boolean isActive = false;
    private LocalDateTime startTime = LocalDateTime.now();
    private LocalDateTime endTime = LocalDateTime.now().plusMinutes(90);

    public Session(Task task) {
        this.task = task;
    }
}