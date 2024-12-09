package com.putfocus.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Session {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "task_id", nullable = false)
    private Task task;

    private int sessionDuration = 90;
    private int currentTimeLeft;

    private boolean completed;
    private boolean isActive;

    private LocalDateTime startTime;
    private LocalDateTime endTime;
}
