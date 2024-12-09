package com.example.backendbooking2.Controller;

import com.example.backendbooking2.Entity.Product;
import com.example.backendbooking2.Service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@CrossOrigin
@RequestMapping("api/product")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;

    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
       List<Product> getAllProducts = productService.getAllProducts();
       return ResponseEntity.ok(getAllProducts);
    }

    /*@GetMapping("/all/{id}")
    public List<Product> getAllProducts(@PathVariable Long id){
        return productService.getAllProductsByAlphabet(id);
    }*/


    // CREATE: Opretter en ny service
    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product createdProduct = productService.createProductService(product);
        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
    }

    // READ: Hent service baseret på ID
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Optional<Product> product = productService.getProductById(id);
        return ResponseEntity.of(product);
    }

    // UPDATE: Opdaterer en eksisterende service
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product updatedProduct) {

        Product updateProduct = productService.updateProduct(id, updatedProduct);
        return new ResponseEntity<>(updateProduct, HttpStatus.CREATED);
    }

    // DELETE: Sletter en service baseret på ID
    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }
}
