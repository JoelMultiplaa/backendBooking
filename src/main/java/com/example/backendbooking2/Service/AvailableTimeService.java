package com.example.backendbooking2.Service;

import com.example.backendbooking2.Entity.AvailableTime;
import com.example.backendbooking2.Repository.AvailableTimeRepository;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.util.List;

@Service
public class AvailableTimeService {

    private final AvailableTimeRepository availableTimeRepository;

    public AvailableTimeService(AvailableTimeRepository availableTimeRepository) {
        this.availableTimeRepository = availableTimeRepository;
    }

    public List<AvailableTime> getAllAvailableTime(LocalDate date){
        return availableTimeRepository.findAllByAvailableDate(date);
    }


    public AvailableTime createAvailableTime(AvailableTime availableTime){
        return availableTimeRepository.save(availableTime);
    }

    public AvailableTime updateByAvailableTime(Long id, AvailableTime updateAvailableTime){
        AvailableTime existingAvailableTime = availableTimeRepository.findById(id)
                .orElseThrow( () -> new RuntimeException("availableTime with ID " + id + " not found"));

        existingAvailableTime.setAvailableDate(updateAvailableTime.getAvailableDate());
        existingAvailableTime.setStartTime(updateAvailableTime.getStartTime());
        existingAvailableTime.setEndTime(updateAvailableTime.getEndTime());

        return availableTimeRepository.save(existingAvailableTime);
    }

    public void deleteAvailableTime(long availableTimeId){
        if (!availableTimeRepository.existsById(availableTimeId)){
            throw new RuntimeException("availableTime with ID " + availableTimeId + " not found");
        }
        availableTimeRepository.deleteById(availableTimeId);
        }

    // Metode til at tjekke, om en given tid er tilgængelig
   public boolean isTimeAvailable(LocalDate date) {
    return !availableTimeRepository.existsByAvailableDate(date);
    }


}
