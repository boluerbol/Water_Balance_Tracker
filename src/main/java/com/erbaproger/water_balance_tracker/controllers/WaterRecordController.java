package com.erbaproger.water_balance_tracker.controllers;

import com.erbaproger.water_balance_tracker.dto.WaterRecordDto;
import com.erbaproger.water_balance_tracker.entities.WaterRecord;
import com.erbaproger.water_balance_tracker.service.WaterRecordService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/water-records")
public class WaterRecordController {


    private final WaterRecordService service;
    private final WaterRecordService waterRecordService;

    @Autowired
    public WaterRecordController(WaterRecordService service, WaterRecordService waterRecordService) {
        this.service = service;
        this.waterRecordService = waterRecordService;
    }

    @GetMapping
    public List<WaterRecord> getAllRecords() {
        return service.getAllRecords();
    }

    @PostMapping("/create")
    public ResponseEntity<WaterRecordDto> createRecord(@Valid @RequestBody WaterRecordDto recordDto) {
        WaterRecordDto createdRecord = waterRecordService.saveRecord(recordDto);
        return ResponseEntity.ok(createdRecord);

    }


    @GetMapping("/getById/{id}")
    public WaterRecord getRecordById(@PathVariable Long id) {
        return service.getRecordById(id);}


    @PutMapping("/update/{id}")
    public WaterRecord updateRecordById(@PathVariable Long id, @RequestBody WaterRecord updatedRecord) {
        return service.updateRecord(id, updatedRecord);
    }


    @DeleteMapping("/delete/{id}")
    public void deleteRecordById(@PathVariable Long id) {
        service.deleteRecord(id);}


}
