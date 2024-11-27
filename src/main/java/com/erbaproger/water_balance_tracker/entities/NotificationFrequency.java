package com.erbaproger.water_balance_tracker.entities;

import lombok.Getter;

@Getter
public enum NotificationFrequency {
    ONE_MINUTE("1 minute"),
    FIVE_MINUTES("5 minutes"),
    THIRTY_MINUTES("30 minutes"),
    ONE_HOUR("1 hour"),
    TWO_HOURS("2 hours"),
    THREE_HOURS("3 hours"),
    FOUR_HOURS("4 hours"),
    FIVE_DAYS("5 days"),
    DAILY("daily");

    private final String displayName;

    NotificationFrequency(String displayName) {
        this.displayName = displayName;
    }

}
