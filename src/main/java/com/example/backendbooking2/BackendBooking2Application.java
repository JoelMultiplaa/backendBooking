package com.example.backendbooking2;

import com.example.backendbooking2.Entity.Category;
import com.example.backendbooking2.Entity.Product;
import com.example.backendbooking2.Repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class BackendBooking2Application {

    public static void main(String[] args) {
        SpringApplication.run(BackendBooking2Application.class, args);
    }

    @Bean
    public CommandLineRunner importData (ProductRepository productRepository){

        return (args) -> {
            List<Product> products = new ArrayList<>();

            products.add(new Product("hdfghrdh", "jdshgdh", "rhyeygur", Category.RAT));

            productRepository.saveAll(products);
        };

    }

}
