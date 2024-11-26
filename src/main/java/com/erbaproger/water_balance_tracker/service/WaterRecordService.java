package com.erbaproger.water_balance_tracker.service;

import com.erbaproger.water_balance_tracker.dto.WaterRecordDto;
import com.erbaproger.water_balance_tracker.entities.User;
import com.erbaproger.water_balance_tracker.entities.WaterRecord;
import com.erbaproger.water_balance_tracker.exceptions.ResourceNotFoundException;
import com.erbaproger.water_balance_tracker.repositories.UserRepository;
import com.erbaproger.water_balance_tracker.repositories.WaterRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class WaterRecordService {

    private final WaterRecordRepository repository;
    private final UserRepository userRepository;

    @Autowired
    public WaterRecordService(WaterRecordRepository repository, UserRepository userRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
    }

    public List<WaterRecord> getAllRecords() {
        return repository.findAll();
    }



    public WaterRecordDto saveRecord(WaterRecordDto waterRecordDto) {
        User user = userRepository.findById(waterRecordDto.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID " + waterRecordDto.getUserId()));
        WaterRecord waterRecord = mapToEntity(waterRecordDto, user);
        WaterRecord saved = repository.save(waterRecord);

        return mapToDTO(saved);
    }




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
    private WaterRecord mapToEntity(WaterRecordDto waterRecordDTO, User user) {
        WaterRecord waterRecord = new WaterRecord();
        waterRecord.setDate(waterRecordDTO.getDate());
        waterRecord.setAmount(waterRecordDTO.getAmount());
        waterRecord.setUser(user); // Set the associated user
        return waterRecord;
    }
    private WaterRecordDto mapToDTO(WaterRecord waterRecord) {
        WaterRecordDto dto = new WaterRecordDto();
        dto.setDate(waterRecord.getDate());
        dto.setAmount(waterRecord.getAmount());
        dto.setUserId(waterRecord.getUser().getId()); // Extract the user ID
        return dto;
    }


}

