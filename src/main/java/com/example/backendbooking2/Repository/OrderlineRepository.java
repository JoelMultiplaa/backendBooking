package com.example.backendbooking2.Repository;

import com.example.backendbooking2.Entity.Orderline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface OrderlineRepository extends JpaRepository<Orderline, Long> {
    List<Orderline> findByOrder_OrderId(Long orderId);
}

