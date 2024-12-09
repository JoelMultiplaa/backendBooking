package com.example.backendbooking2.Repository;

import com.example.backendbooking2.Entity.Category;
import com.example.backendbooking2.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

   List<Product> findAllByCategory(Category category);
}
