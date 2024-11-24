package com.example.backendbooking2.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Primærnøgle

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    @DateTimeFormat(pattern = "HH:mm:ss")
    private LocalTime localTime;

    @OneToMany(mappedBy = "booking")
    private List<Customer> customers;

    @OneToMany(mappedBy = "booking")
    private List<Admin> admins;

    @OneToMany(mappedBy = "booking")
    private List<Orderline> orders;
}