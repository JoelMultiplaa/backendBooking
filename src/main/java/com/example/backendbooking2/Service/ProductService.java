package com.example.backendbooking2.Service;

import com.example.backendbooking2.Entity.Service;
import com.example.backendbooking2.Repository.ServiceRepository;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Service
public class ProductService {

    private final ServiceRepository serviceRepository;

    public ProductService(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    // CREATE: Opretter en ny service
    public org.springframework.stereotype.Service createProductService(org.springframework.stereotype.Service service) {
        return Optional.ofNullable(service)
                .map(serviceRepository::save) // Gemmer service i databasen
                .orElseThrow(() -> new RuntimeException("Invalid Service data")); // Kaster undtagelse hvis service er null
    }

    // READ - Hent alle services
    public List<org.springframework.stereotype.Service> getAllProductServices() {
        // Henter alle services fra databasen og returnerer dem som en liste
        return serviceRepository.findAll(); // Returnerer listen direkte uden at pakke den i Optional
    }

    // READ - Hent service baseret på ID
    public org.springframework.stereotype.Service getProductServicesById(Long serviceId) {
        // Forsøger at finde en service med det givne serviceId
        return serviceRepository.findById(serviceId)
                .orElseThrow(() -> new RuntimeException("Service not found")); // Kaster undtagelse hvis service ikke findes
    }

    // UPDATE - Opdaterer en eksisterende service
    public Service updateProductService(Long serviceId, Service updatedService) {
        return serviceRepository.findById(serviceId)
                .map(existingService -> {
                    // Hvis fundet, opdaterer de relevante felter i den eksisterende service
                    existingService.setName(updatedService.getName());
                    existingService.setDescription(updatedService.getDescription());
                    return serviceRepository.save(existingService); // Gemmer den opdaterede service og returnerer den
                })
                .orElseThrow(() -> new RuntimeException("Service not found"));
    }

    // DELETE - Sletter en service baseret på ID
    public void deleteService(Long serviceId) {
        serviceRepository.findById(serviceId)
                .ifPresentOrElse(service -> serviceRepository.deleteById(serviceId),
                        () -> { throw new RuntimeException("Service not found"); }); // Bruger ifPresentOrElse til at slette eller kaste undtagelse
    }
}
