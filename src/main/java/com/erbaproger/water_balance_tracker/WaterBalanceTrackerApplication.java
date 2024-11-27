package com.erbaproger.water_balance_tracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableScheduling
public class WaterBalanceTrackerApplication {

	public static void main(String[] args) {
		SpringApplication.run(WaterBalanceTrackerApplication.class, args);
	}

}
