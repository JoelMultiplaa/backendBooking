package com.example.backendbooking2.Service;

import com.example.backendbooking2.Entity.Product;
import com.example.backendbooking2.Repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class ProductServiceTest {
    private ProductService product;
    @Mock
    private ProductRepository repository;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        product = new ProductService(repository);
    }
    public List<Product> fakeProducts(){
        Product product1 = new Product();
        Product product2 = new Product();
        Product product3 = new Product();
        Product product4 = new Product();
        Product product5 = new Product();
        product1.setName("a 1");
        product2.setName("b 2");
        product3.setName("c 3");
        product4.setName("ab 12");
        product5.setName("bc 23");
        return Arrays.asList(product1,product2,product3,product4,product5);

    }
    @Test
    void getAllProductsByAlphabetically() {
        when(repository.findAll()).thenReturn(fakeProducts());
        System.out.println("Alphabeticly Ordered");
        List<Product> sortedProduct = product.getAllProductsByAlphabet(1);
        sortedProduct.forEach(product-> System.out.println("Product: "+product.getName()+"\n"));

        assertEquals("a 1",sortedProduct.get(0).getName());
        assertEquals("ab 12",sortedProduct.get(1).getName());
        assertEquals("b 2",sortedProduct.get(2).getName());
        assertEquals("bc 23",sortedProduct.get(3).getName());
        assertEquals("c 3",sortedProduct.get(4).getName());
    }
    @Test
    void getAllProductsByReversedAlphabet() {
        // Arrange
        when(repository.findAll()).thenReturn(fakeProducts());
        System.out.println("Reversed Ordering");
        // Act
        List<Product> sortedProducts = product.getAllProductsByAlphabet(0);
        sortedProducts.forEach(product-> System.out.println("Product: "+product.getName()+"\n"));

        // Assert
        assertEquals("c 3", sortedProducts.get(0).getName());
        assertEquals("bc 23", sortedProducts.get(1).getName());
        assertEquals("b 2", sortedProducts.get(2).getName());
        assertEquals("ab 12", sortedProducts.get(3).getName());
        assertEquals("a 1", sortedProducts.get(4).getName());
    }
    @Test
    void getAllProductsByNaturalAlphabet() {
        // Arrange
        List<Product> fakeData = fakeProducts();
        when(repository.findAll()).thenReturn(fakeData);
        System.out.println("Normal Ordering");
        // Act
        List<Product> naturalOrderProducts = product.getAllProductsByAlphabet(null);
        naturalOrderProducts.forEach(product-> System.out.println("Product: "+product.getName()+"\n"));

        // Assert
        assertEquals("a 1", naturalOrderProducts.get(0).getName());
        assertEquals("b 2", naturalOrderProducts.get(1).getName());
        assertEquals("c 3", naturalOrderProducts.get(2).getName());
        assertEquals("ab 12", naturalOrderProducts.get(3).getName());
        assertEquals("bc 23", naturalOrderProducts.get(4).getName());
    }
}