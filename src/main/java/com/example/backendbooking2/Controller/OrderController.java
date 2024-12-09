package com.example.backendbooking2.Controller;

import com.example.backendbooking2.DTO.OrderConfirmationResponse;
import com.example.backendbooking2.Entity.Order;
import com.example.backendbooking2.Repository.OrderRepository;
import com.example.backendbooking2.Service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService){
        this.orderService = orderService;
    }


    // Henter alle orders
    @GetMapping
    public ResponseEntity<List<Order>> getAllOrder(){
        List<Order> orders = orderService.getAllOrder();
        return ResponseEntity.ok(orders);
    }

    // Opretter en ny order
    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody Order order){
        Order createOrder = orderService.createOrder(order);
        return ResponseEntity.ok(createOrder);
    }

    // Opdaterer en order efter ID
    @PutMapping("/{Id}")
    public ResponseEntity<Order> updateOrder(@PathVariable Long Id, @RequestBody Order updatedOrder){
        try{
            Order order = orderService.updateOrderId(Id, updatedOrder);
            return ResponseEntity.ok(order);
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // Sletter en order efter ID
    @DeleteMapping("/{Id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long Id){
        try {
            orderService.deleteOrder(Id);
            return ResponseEntity.noContent().build();

        } catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}
