package com.example.backendbooking2.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository

public interface CustomerRepository extends JpaRepository<Service, String> {
}
