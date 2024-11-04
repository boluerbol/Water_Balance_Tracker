package com.erbaproger.water_balance_tracker.service;

import com.erbaproger.water_balance_tracker.entities.WaterRecord;
import com.erbaproger.water_balance_tracker.repositories.WaterRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WaterRecordService {
    @Autowired
    private WaterRecordRepository repository;
    public List<WaterRecord> getAllRecords() { return repository.findAll(); }
    public WaterRecord saveRecord(WaterRecord record) { return repository.save(record); }
    // More CRUD methods
}

