package com.erbaproger.water_balance_tracker.entities;


import jakarta.persistence.GenerationType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int dailyWaterGoal; // daily goal in milliliters
    private String email; // for notifications, if needed
    private boolean notificationsEnabled;
    private String notificationFrequency; // e.g., "hourly", "every 2 hours"
}