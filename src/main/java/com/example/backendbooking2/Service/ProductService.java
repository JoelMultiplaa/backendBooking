package com.example.backendbooking2.Service;

import com.example.backendbooking2.Entity.Product;
import com.example.backendbooking2.Repository.ProductRepository;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;


@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository serviceRepository) {
        this.productRepository = serviceRepository;
    }



    // CREATE: Opretter en ny service
    public Optional<Product> createProductService(Product product) {
        Optional<Product> createProduct = Optional.of(productRepository.save(product));
        return createProduct ;
    }



    // READ - Hent alle services
    public Optional<List<Product>> getAllProductNames() {
        List<Product> productList = productRepository.findAll();
        return Optional.of(productList.stream().toList());
    }



    // READ - Hent service baseret på ID
    public Optional<Product> getProductServicesById(Long serviceId) {
        return Optional.ofNullable(serviceId) // Håndterer potentielt null serviceId
                .map(id -> productRepository.findById(id)) // Finder produktet baseret på id
                .orElse(Optional.empty()); // Returnerer tom Optional, hvis serviceId er null
    }



    // UPDATE - Opdaterer en eksisterende service
    public Product updateProduct(Long productId, Product updatedProduct) {
        Product existingPrduct = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product with ID " + productId + " not found"));

        existingPrduct.setImageURL(updatedProduct.getImageURL());
        existingPrduct.setName(updatedProduct.getName());
        existingPrduct.setDescription(updatedProduct.getDescription());

        return productRepository.save(existingPrduct);
    }

    // DELETE - Sletter en service baseret på ID
    public void deleteProduct(Long Id) {
        if (!productRepository.existsById(Id)){
            throw new RuntimeException("Product with ID " + Id + " not found");
        }
        productRepository.deleteById(Id);
    }
}
