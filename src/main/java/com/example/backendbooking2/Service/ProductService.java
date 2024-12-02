package com.example.backendbooking2.Service;

import com.example.backendbooking2.Entity.Product;
import com.example.backendbooking2.Repository.ProductRepository;
import org.springframework.stereotype.Service;



import java.util.Optional;


@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository serviceRepository) {
        this.productRepository = serviceRepository;
    }



    // CREATE: Opretter en ny service
    public Product createProductService(Product product) {
        return productRepository.save(product);
    }



    // READ - Hent alle product baseret på ID
    public Optional<Product> getAllProductById(Long id) {
        return productRepository.findById(id);
    }


    // UPDATE - Opdaterer en eksisterende service
    public Product updateProduct(Long productId, Product updatedProduct) {
        Product existingProduct = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product with ID " + productId + " not found"));

        existingProduct.setImageURL(updatedProduct.getImageURL());
        existingProduct.setName(updatedProduct.getName());
        existingProduct.setDescription(updatedProduct.getDescription());

        return productRepository.save(existingProduct);
    }

    // DELETE - Sletter en service baseret på ID
    public void deleteProduct(Long Id) {
        if (!productRepository.existsById(Id)){
            throw new RuntimeException("Product with ID " + Id + " not found");
        }
        productRepository.deleteById(Id);
    }
}
