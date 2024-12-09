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
    private Long productId;

    private String description;
    private String name;
    private String imageURL;

    public Product(String description, String name, String imageURL, Category category) {
        this.description = description;
        this.name = name;
        this.imageURL = imageURL;
        this.category = category;
    }

    private Category category;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<Orderline> orderlines;
}