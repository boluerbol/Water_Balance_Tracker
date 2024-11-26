package com.erbaproger.water_balance_tracker.entities;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.*;

import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Name is required BRO!!!")
    private String name;

    @Min(value = 0, message = "You have to drink not vomit :) ")
    private int dailyWaterGoal;

    @Email(message = "What is that? EMAIL!!!")
    private String email;

    private boolean notificationsEnabled;

    @Enumerated(EnumType.STRING)
    private NotificationFrequency notificationFrequency;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<WaterRecord> waterRecords;


}