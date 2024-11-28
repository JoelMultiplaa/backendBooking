package com.example.backendbooking2.Repository;

import com.example.backendbooking2.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
