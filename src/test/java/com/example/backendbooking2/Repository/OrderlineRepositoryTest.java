package com.example.backendbooking2.Repository;

import com.example.backendbooking2.Entity.Order;
import com.example.backendbooking2.Entity.Orderline;
import com.example.backendbooking2.Entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
/*
*
@DataJpaTest
class OrderlineRepositoryTest {

    @Autowired
    private OrderlineRepository orderlineRepository;

    @Autowired
    private OrderRepository orderRepository; // Assuming you have an OrderRepository

    @Autowired
    private ProductRepository productRepository; // Assuming you have a ProductRepository

    @Test
    void testFindByOrder_OrderId() {
        // Arrange: Create and save necessary entities
        Order order = new Order();
        order.setStartDate(LocalDate.now());
        order.setLocalTime(LocalTime.now());
        order.setWorkShopLocation("Test Location");
        order = orderRepository.save(order);

        Product product = new Product();
        product.setName("Test Product");
        product.setDescription("Test Description");
        product = productRepository.save(product);

        Orderline orderline1 = new Orderline();
        orderline1.setQuantity(2);
        orderline1.setOrder(order);
        orderline1.setService(product);

        Orderline orderline2 = new Orderline();
        orderline2.setQuantity(5);
        orderline2.setOrder(order);
        orderline2.setService(product);

        orderlineRepository.save(orderline1);
        orderlineRepository.save(orderline2);

        // Act: Call the repository method
        List<Orderline> orderlines = orderlineRepository.findByOrder_OrderId(order.getOrderId());

        orderlines.forEach(p-> System.out.println("This Is P:" + p));
        // Assert: Verify the result
        assertThat(orderlines).hasSize(2);
        assertThat(orderlines.get(0).getOrder().getOrderId()).isEqualTo(order.getOrderId());
        assertThat(orderlines.get(1).getOrder().getOrderId()).isEqualTo(order.getOrderId());
    }
}
*/