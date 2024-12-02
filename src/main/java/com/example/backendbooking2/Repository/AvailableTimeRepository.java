package com.example.backendbooking2.Repository;

import com.example.backendbooking2.Entity.AvailableTime;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;


public interface AvailableTimeRepository extends JpaRepository<AvailableTime, Long> {
    // Tjekker, om en bestemt dato og tid er tilgængelig
   boolean existsByAvailableDate(LocalDate availableDate);


    // Finder en specifik tilgængelig tid baseret på dato og starttid
    List<AvailableTime> findAllByAvailableDate(LocalDate availableDate);
}
