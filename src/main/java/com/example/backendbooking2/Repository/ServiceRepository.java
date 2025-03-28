package com.example.backendbooking2.Repository;


import com.example.backendbooking2.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.Optional;

@Repository
public interface ServiceRepository extends JpaRepository<Product, Long> {
 Optional<Product> findById(Long id);
}
