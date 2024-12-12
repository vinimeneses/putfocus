// SessionController.java
package com.putfocus.controller;

import com.putfocus.entities.Session;
import com.putfocus.service.SessionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sessions")
public class SessionController {

    private final SessionService sessionService;

    public SessionController(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @PostMapping("/{id}")
    public ResponseEntity<Session> startSession(@PathVariable Long id, @RequestBody Session session) {
        sessionService.startSession(id, session);
        return ResponseEntity.ok(session);
    }
}