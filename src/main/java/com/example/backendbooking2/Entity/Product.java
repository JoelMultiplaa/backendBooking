package com.example.backendbooking2.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId; // Primærnøgle
    private String imageURL;
    private String description;
    private String name;
    @Enumerated(EnumType.STRING)
    private Category category;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "product")
    private List<Orderline> orderline;
}