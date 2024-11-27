package com.example.backendbooking2.Service;

import com.example.backendbooking2.Entity.Product;
import com.example.backendbooking2.Repository.ServiceRepository;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ServiceRepository serviceRepository;

    public ProductService(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }



    // CREATE: Opretter en ny service
    public Optional<Product> createProductService(Product product) {
        /*return Optional.ofNullable(service)
                .map(serviceRepository::save) // Gemmer service i databasen
                .orElseThrow(() -> new RuntimeException("Invalid Service data"));*/ // Kaster undtagelse hvis service er null
        Optional<Product> createProduct = Optional.of(serviceRepository.save(product));
        return createProduct ;
    }

   /* // READ - Hent alle services
    public Optional<List<?>> getAllProductName() {

        // Henter alle services fra databasen og returnerer dem som en liste
        List<Product> productList = serviceRepository.findAll();

        return Optional.of(productList); // Returnerer listen direkte uden at pakke den i Optional
    }*/

    // READ - Hent alle services
    public Optional<List<?>> getAllProductName() {
        return Optional.ofNullable( // ændre til ".of()" hvis den returnerer en liste
                serviceRepository.findAll().stream()
                        .map(Product::getName)
                        .collect(Collectors.toList())
        );
    }



    // READ - Hent service baseret på ID
    public Optional<Product> getProductServicesById(Long serviceId) {
        return Optional.ofNullable(serviceId) // Håndterer potentielt null serviceId
                .map(id -> serviceRepository.findById(id)) // Finder produktet baseret på id
                .orElse(Optional.empty()); // Returnerer tom Optional, hvis serviceId er null
    }

    // UPDATE - Opdaterer en eksisterende service
    public Optional<?> updateProductService(Long serviceId, Product updatedProduct) {
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
