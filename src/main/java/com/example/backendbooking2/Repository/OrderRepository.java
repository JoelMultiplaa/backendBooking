package com.example.backendbooking2.Repository;

import com.example.backendbooking2.Entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;


@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    boolean existsByAvailableDate(LocalDate availableDate);


    // Finder en specifik tilgængelig tid baseret på dato og starttid
    List<Order> findAllByAvailableDate(LocalDate availableDate);
}


