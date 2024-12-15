package com.example.backendbooking2.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    private String description;
    private String name;
    private String imageURL;
    @Enumerated(EnumType.STRING)
    private Category category;

    public Product(String description, String name, String imageURL, Category category) {
        this.description = description;
        this.name = name;
        this.imageURL = imageURL;
        this.category = category;
    }

@JsonIgnore
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Orderline> orderlines;
}