package com.example.backendbooking2.Repository;

import com.example.backendbooking2.Entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByDateAndStartTimeBetween(LocalDate date, LocalTime start, LocalTime end);
}
