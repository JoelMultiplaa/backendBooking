package com.example.backendbooking2.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "orderline")

public class Orderline {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderlineId;

    private int quantity;
    @OneToOne(mappedBy = "orderline",fetch = FetchType.EAGER)
    private Order order;

    @OneToMany(mappedBy = "orderline",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Product> products; // Sørg for at bruge den korrekte klasse her
}
