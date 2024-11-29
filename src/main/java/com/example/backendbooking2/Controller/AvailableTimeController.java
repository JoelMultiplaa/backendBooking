package com.example.backendbooking2.Controller;

import com.example.backendbooking2.Entity.AvailableTime;
import com.example.backendbooking2.Entity.Order;
import com.example.backendbooking2.Entity.Product;
import com.example.backendbooking2.Service.AvailableTimeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/availableTime")
public class AvailableTimeController {
    private AvailableTimeService availableTimeService;

    public void setAvailableTimeService(AvailableTimeService availableTimeService) {
        this.availableTimeService = availableTimeService;
    }

    // READ: Hent alle AvailableTimes
    @GetMapping
    public ResponseEntity<List<AvailableTime>> getAllAvailableTimes(){
        List<AvailableTime> availableTimes = availableTimeService.getAllAvailableTime();
        return ResponseEntity.ok(availableTimes);
    }

    // CREATE: Opretter en ny availableTime
    @PostMapping
    public ResponseEntity<AvailableTime> createAvaibleTime(@RequestBody AvailableTime availableTime){
        AvailableTime createdAvailableTime = availableTimeService.createAvailableTime(availableTime);
                return ResponseEntity.ok(createdAvailableTime);
    }

    // UPDATE: Opdaterer en eksisterende availableTime baseret på ID
    @PutMapping("/{id}")
    public ResponseEntity<AvailableTime> update (@PathVariable Long id, @RequestBody AvailableTime updateAvailableTime){
        try{
            AvailableTime availableTime = availableTimeService.updateByAvailableTime(id, updateAvailableTime);
            return ResponseEntity.ok(availableTime);
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }



    // DELETE: Sletter en availableTime baseret på ID
    @DeleteMapping("/{id}")
    public void deleteAvailableTime(@PathVariable Long id){
        availableTimeService.deleteAvailableTime(id);
    }
}
