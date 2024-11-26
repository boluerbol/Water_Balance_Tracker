package com.erbaproger.water_balance_tracker.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class UserDto {
    private Long id;
    private String name;
    private int dailyWaterGoal;
    private String email;
    private boolean notificationsEnabled;
    private String notificationFrequency;
    private List<WaterRecordDto> waterRecords;
}
