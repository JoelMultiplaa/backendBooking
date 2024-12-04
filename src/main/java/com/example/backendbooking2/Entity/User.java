package com.example.backendbooking2.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "user")
public class User {//this is employee/admin
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId; // Primærnøgle

    private String name;
    private String email;
    private String password;

    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY)
    private List<Order> order = new ArrayList<>();
}