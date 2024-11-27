package com.example.backendbooking2.Repository;

import com.example.backendbooking2.Entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalTime;

public interface OrderRepository extends JpaRepository<Order, Long> {

    boolean existsByStartDateAndLocalTime(LocalDate startData, LocalTime localTime);
}
