package com.example.backendbooking2.Service;


import java.util.Comparator;
import java.util.List;

import com.example.backendbooking2.Entity.Category;
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



    public List<Product> getAllProducts() {
       return productRepository.findAll();
    }

    public List<Product> findByCategory(Category category) {
        return productRepository.findAllByCategory(category); // Tilpas denne metode til din repository
    }


    /**To Sort Product By Order Type.
     * @param alpha
     *              1 is for alphabeticly ordered,
 *                  0 is for reversed-alphabeticly ordered,
 *                  other (or null) is for natural order.
     * @return Sorted by the type of input.
     */
    public List<Product> getAllProductsByAlphabet(Long alpha){
        List<Product> sortProducts = productRepository.findAll();
        if (Long.valueOf(1).equals(alpha)) {
           return sortProducts
                            .stream()
                            .sorted(Comparator.comparing(Product::getName)) //NOTE: This is for sorting products by name.
                            .toList();
        }
        if (Long.valueOf(0).equals(alpha)){

            return sortProducts
                    .stream()
                    .sorted(Comparator.comparing(Product::getName))//NOTE: This is for sorting products by name.
                    .toList()
                    .reversed();
        }
            return sortProducts;
    }
    // CREATE: Opretter en ny service
    public Product createProductService(Product product) {
        return productRepository.save(product);
    }


    // READ - Hent alle product baseret på ID
    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }


    // UPDATE - Opdaterer en eksisterende service
    public Product updateProduct(Long productId, Product updatedProduct) {
        Product existingProduct = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product with ID " + productId + " not found"));

        existingProduct.setImageURL(updatedProduct.getImageURL());
        existingProduct.setName(updatedProduct.getName());
        existingProduct.setDescription(updatedProduct.getDescription());
        existingProduct.setCategory(updatedProduct.getCategory());

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
