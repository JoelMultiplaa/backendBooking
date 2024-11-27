package com.example.backendbooking2.Repository;

import com.example.backendbooking2.Entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Repository

public interface CustomerRepository extends JpaRepository<Customer, Long> {


}
