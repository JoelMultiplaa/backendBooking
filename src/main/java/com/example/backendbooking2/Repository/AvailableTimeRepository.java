package com.example.backendbooking2.Repository;

import com.example.backendbooking2.Entity.AvailableTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

@Repository
public interface AvailableTimeRepository extends JpaRepository<AvailableTime, Long> {
    // Tjekker, om en bestemt dato og tid er tilgængelig
    boolean existsByDateAndStartTime(LocalDate date, LocalTime startTime);


    // Finder en specifik tilgængelig tid baseret på dato og starttid
    Optional<AvailableTime> findByDateAndStartTime(LocalDate date, LocalTime startTime);
}
