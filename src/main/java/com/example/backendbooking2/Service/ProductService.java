package com.example.backendbooking2.Service;

import com.example.backendbooking2.Entity.Product;
import com.example.backendbooking2.Repository.ProductRepository;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;


@Service
public class ProductService {

    private final ProductRepository serviceRepository;

    public ProductService(ProductRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }



    // CREATE: Opretter en ny service
    public Optional<Product> createProductService(Product product) {
        Optional<Product> createProduct = Optional.of(serviceRepository.save(product));
        return createProduct ;
    }



    // READ - Hent alle services
    public Optional<List<Product>> getAllProductNames() {
        List<Product> productList = serviceRepository.findAll();
        return Optional.of(productList.stream().toList());
    }



    // READ - Hent service baseret på ID
    public Optional<Product> getProductServicesById(Long serviceId) {
        return Optional.ofNullable(serviceId) // Håndterer potentielt null serviceId
                .map(id -> serviceRepository.findById(id)) // Finder produktet baseret på id
                .orElse(Optional.empty()); // Returnerer tom Optional, hvis serviceId er null
    }

    // UPDATE - Opdaterer en eksisterende service
    public Optional<Product> updateProductService(Long serviceId, Product updatedProduct) {
        Optional<Product> updatingService=serviceRepository.findById(serviceId);
        updatingService.map(exsist->{
                updatedProduct.setName(exsist.getName());
                updatedProduct.setDescription(exsist.getDescription());
                return updatedProduct;
                })
                .orElseThrow(()-> new RuntimeException("Product not found"));
        return Optional.empty();
    }

    // DELETE - Sletter en service baseret på ID
    public void deleteService(Long serviceId) {
        serviceRepository.findById(serviceId)
                .ifPresentOrElse(product -> serviceRepository.deleteById(serviceId),
                        () -> { throw new RuntimeException("Service not found"); }); // Bruger ifPresentOrElse til at slette eller kaste undtagelse
    }
}
