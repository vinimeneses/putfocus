package com.putfocus.entities;

import jakarta.persistence.*;
import lombok.*;


@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private String title;
    private String description;
    @Column(nullable = false)
    private int sessionEstimate;
    private int currentSession = 0;
    private boolean completed;

    public Task(Long id, String title, String description, int sessionEstimate, int currentSession, boolean completed) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.sessionEstimate = sessionEstimate;
        this.currentSession = currentSession;
        this.completed = completed;
    }
    public Task() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getSessionEstimate() {
        return sessionEstimate;
    }

    public void setSessionEstimate(int sessionEstimate) {
        this.sessionEstimate = sessionEstimate;
    }

    public int getCurrentSession() {
        return currentSession;
    }

    public void setCurrentSession(int currentSession) {
        this.currentSession = currentSession;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }


}