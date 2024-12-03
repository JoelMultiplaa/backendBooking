package com.example.backendbooking2.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId; // Primærnøgle

    private String email;
    private String phoneNumber;
    private String city;
    private String name;
    private String licensePlate;

    @ManyToOne
    @JoinColumn(name = "booking_id")
    private Order order;
}