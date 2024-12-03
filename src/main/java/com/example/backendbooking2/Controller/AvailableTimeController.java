package com.example.backendbooking2.Controller;

import com.example.backendbooking2.Entity.AvailableTime;
import com.example.backendbooking2.Service.AvailableTimeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;


@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/availableTime")
public class AvailableTimeController {
    private final AvailableTimeService availableTimeService;

    public AvailableTimeController(AvailableTimeService availableTimeService) {
        this.availableTimeService = availableTimeService;
    }


    // READ: Hent alle AvailableTimes
    @GetMapping
    public ResponseEntity<List<AvailableTime>> getAllAvailableTimes(LocalDate date){
        List<AvailableTime> availableTimes = availableTimeService.getAllAvailableTime(date);
        return ResponseEntity.ok(availableTimes);
    }

    // CREATE: Opretter en ny availableTime
    @PostMapping
    public ResponseEntity<AvailableTime> createAvailableTime(@RequestBody AvailableTime availableTime){
        AvailableTime createdAvailableTime = availableTimeService.createAvailableTime(availableTime);
                return ResponseEntity.ok(createdAvailableTime);
    }

    // UPDATE: Opdaterer en eksisterende availableTime baseret på ID
    @PutMapping("/{id}")
    public ResponseEntity<AvailableTime> updateAvailableTime (@PathVariable Long id, @RequestBody AvailableTime updateAvailableTime){
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
