package com.example.backendbooking2.Controller;

import com.example.backendbooking2.Entity.Product;
import com.example.backendbooking2.Service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("api/v1")
public class ProductController {
    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;

    }
    // CREATE: Opretter en ny service
    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Optional<Product> createdProduct = productService.createProductService(product);
        return ResponseEntity.status(201).body(createdProduct.orElseThrow(() -> new RuntimeException("Failed to create product")));
    }

    // READ: Hent alle services
    @GetMapping
    public ResponseEntity<List<Product>> getAllProductNames() {
        Optional<List<Product>> productNames = productService.getAllProductNames();
        return ResponseEntity.ok(productNames.orElseThrow(() -> new RuntimeException("No products found")));
    }

    // READ: Hent service baseret på ID
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Optional<Product> product = productService.getProductServicesById(id);
        return ResponseEntity.of(product);
    }

    // UPDATE: Opdaterer en eksisterende service
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateProduct(@PathVariable Long id, @RequestBody Product updatedProduct) {
        productService.updateProductService(id, updatedProduct);
        return ResponseEntity.noContent().build();
    }

    // DELETE: Sletter en service baseret på ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long serviceId) {
        productService.deleteService(serviceId);
        return ResponseEntity.noContent().build();
    }
}
