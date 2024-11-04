package com.erbaproger.water_balance_tracker.controllers;

import com.erbaproger.water_balance_tracker.entities.WaterRecord;
import com.erbaproger.water_balance_tracker.service.WaterRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/water-records")
public class WaterRecordController {
    @Autowired
    private WaterRecordService service;
    @GetMapping
    public List<WaterRecord> getAllRecords() { return service.getAllRecords(); }
    @PostMapping
    public WaterRecord createRecord(@RequestBody WaterRecord record) { return service.saveRecord(record); }
    // More endpoints
}
