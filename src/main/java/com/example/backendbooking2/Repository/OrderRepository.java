package com.example.backendbooking2.Repository;

import com.example.backendbooking2.Entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
