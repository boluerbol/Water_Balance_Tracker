package com.erbaproger.water_balance_tracker.service;

import com.erbaproger.water_balance_tracker.entities.WaterRecord;
import com.erbaproger.water_balance_tracker.exceptions.ResourceNotFoundException;
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

    public WaterRecord getRecordById(Long id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Record not found with id " + id));
    }


    public WaterRecord updateRecord(Long id, WaterRecord updatedRecord) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Record not found with id " + id);
        }
        updatedRecord.setId(id);
        return repository.save(updatedRecord);
    }

    public void deleteRecord(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Record not found with id " + id);
        }

        repository.deleteById(id);
    }
}

