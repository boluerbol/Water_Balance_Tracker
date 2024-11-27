package com.erbaproger.water_balance_tracker.service;

import com.erbaproger.water_balance_tracker.entities.User;
import com.erbaproger.water_balance_tracker.entities.NotificationFrequency;
import com.erbaproger.water_balance_tracker.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.erbaproger.water_balance_tracker.entities.NotificationFrequency.*;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Service
public class NotifService {

    @Autowired
    private JavaMailSender mailSender;
    private final UserRepository userRepository;

    @Autowired
    public NotifService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private long getNotificationInterval(NotificationFrequency frequency) {
        return switch (frequency) {
            case ONE_MINUTE -> TimeUnit.MINUTES.toMillis(1);
            case FIVE_MINUTES -> TimeUnit.MINUTES.toMillis(5);
            case THIRTY_MINUTES -> TimeUnit.MINUTES.toMillis(30);
            case ONE_HOUR -> TimeUnit.HOURS.toMillis(1);
            case TWO_HOURS -> TimeUnit.HOURS.toMillis(2);
            case THREE_HOURS -> TimeUnit.HOURS.toMillis(3);
            case FOUR_HOURS -> TimeUnit.HOURS.toMillis(4);
            case FIVE_DAYS -> TimeUnit.DAYS.toMillis(5);
            case DAILY -> TimeUnit.DAYS.toMillis(1);
            default -> throw new IllegalArgumentException("Unsupported notification frequency: " + frequency);
        };
    }

    @Scheduled(cron = "0 * * * * *") // Runs every minute for demo purposes
    public void sendNotifications() {
        LocalDateTime now = LocalDateTime.now();

        List<User> users = userRepository.findAll();
        for (User user : users) {
            if (user.isNotificationsEnabled()) {
                NotificationFrequency frequency = user.getNotificationFrequency();
                long interval = getNotificationInterval(frequency);

                if (shouldSendNotification(user, now, interval)) {
                    notifyUser(user, now);
                }
            }
        }
    }

    private boolean shouldSendNotification(User user, LocalDateTime now, long interval) {
        LocalDateTime lastNotificationTime = user.getLastNotificationTime();

        // If the user has never been notified, send notification
        if (lastNotificationTime == null) {
            return true;
        }

        // Calculate time since the last notification
        long timeSinceLastNotification = ChronoUnit.MILLIS.between(lastNotificationTime, now);

        return timeSinceLastNotification >= interval;
    }

    private void notifyUser(User user, LocalDateTime now) {
        // Send email notification
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(user.getEmail());
        message.setSubject("Hydration Reminder");
        message.setText("Hello " + user.getName() + ",\n\nDon't forget to drink water! Your daily goal is: "
                + user.getDailyWaterGoal() + " ml.\n\nStay hydrated!");
        mailSender.send(message);

        // Update user's last notification time
        user.setLastNotificationTime(now);
        userRepository.save(user);

        System.out.println("Notification sent to: " + user.getEmail());
    }
}
