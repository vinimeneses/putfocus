package com.putfocus.service.impl;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class TimerService {

    private LocalDateTime endTime = LocalDateTime.now().plusSeconds(10);

    private boolean timeUp = false;

    @Scheduled(fixedRate = 1000)
    public void countdown() {
        LocalDateTime now = LocalDateTime.now();

        if (!now.isBefore(endTime) && !timeUp) {
            timeUp = true;
            System.out.println("O tempo acabou!");
            return;
        }

        Duration remainingDuration = Duration.between(now, endTime);

        if (remainingDuration.isNegative() || remainingDuration.isZero()) {
            System.out.println("O tempo acabou!");
            return;
        }

        String formattedTime = formatTime(remainingDuration);

        System.out.println("Tempo restante: " + formattedTime);
    }

    private String formatTime(Duration duration) {
        long hours = duration.toHours();
        long minutes = duration.toMinutes() % 60;
        long seconds = duration.getSeconds() % 60;

        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }
}
