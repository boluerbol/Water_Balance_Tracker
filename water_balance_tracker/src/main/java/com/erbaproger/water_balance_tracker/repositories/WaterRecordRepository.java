package com.erbaproger.water_balance_tracker.repositories;

import com.erbaproger.water_balance_tracker.entities.WaterRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WaterRecordRepository extends JpaRepository<WaterRecord, Long> { }

