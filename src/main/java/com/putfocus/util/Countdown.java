package com.putfocus.util;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Timer;
import java.util.TimerTask;

public class Countdown {
    private static LocalDateTime endTime;
    private static Timer timer;

    public static void startCountdown(int minutes) {
        int delay = 1000;
        int period = 1000;
        timer = new Timer();
        endTime = LocalDateTime.now().plusMinutes(minutes);
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                System.out.println(getRemainingTime());
            }
        }, delay, period);
    }

    private static String getRemainingTime() {
        LocalDateTime now = LocalDateTime.now();
        Duration duration = Duration.between(now, endTime);
        long seconds = duration.getSeconds();

        if (seconds <= 0) {
            timer.cancel();
            return "00:00:00";
        }

        long hours = seconds / 3600;
        long minutes = (seconds % 3600) / 60;
        long remainingSeconds = seconds % 60;

        return String.format("%02d:%02d:%02d", hours, minutes, remainingSeconds);
    }
}