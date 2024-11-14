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
    public List<WaterRecord> getAllRecords() {
        return service.getAllRecords(); }


    @PostMapping("/create")
    public WaterRecord createRecord(@RequestBody WaterRecord record) {
        return service.saveRecord(record); }


    @GetMapping("/getById/{id}")
    public WaterRecord getRecordById(@PathVariable Long id) {
        return service.getRecordById(id);}


    @PutMapping("/update/{id}")
    public WaterRecord updateRecordById(@PathVariable Long id, @RequestBody WaterRecord updatedRecord) {
        return service.updateRecord(id, updatedRecord);}


    @DeleteMapping("/delete/{id}")
    public void deleteRecordById(@PathVariable Long id) {
        service.deleteRecord(id);}


}
