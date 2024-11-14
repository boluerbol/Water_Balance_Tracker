package com.erbaproger.water_balance_tracker.repositories;

import com.erbaproger.water_balance_tracker.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
