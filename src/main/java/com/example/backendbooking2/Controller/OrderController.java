package com.example.backendbooking2.Controller;

import com.example.backendbooking2.DTO.OrderConfirmationResponse;
import com.example.backendbooking2.Entity.Order;
import com.example.backendbooking2.Service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
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
    public ResponseEntity<OrderConfirmationResponse> createOrder(@RequestBody Order order){

        try {
            Order createdOrder = orderService.createOrder(order);

            // Returner en bekræftelsesbesked med ordren
            OrderConfirmationResponse response = new OrderConfirmationResponse(
                    createdOrder.getOrderId(),
                    createdOrder.getStartDate(),
                    createdOrder.getLocalTime(),
                    createdOrder.getOrderlines(),
                    createdOrder.getCustomers(),
                    "Order successfully created."
            );
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (RuntimeException e) {
            // Hvis der opstår en fejl, returneres en fejlmeddelelse
            OrderConfirmationResponse errorResponse = new OrderConfirmationResponse(
                    null, // Ingen ordreId
                    null, // Ingen startdato
                    null, // Ingen klokkeslæt
                    null, // Ingen ordrelinjer
                    null, // Ingen kunder
                    "Failed to create order: " + e.getMessage()
            );

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

    // Opdaterer en order efter ID
    @PutMapping("/{orderID}")
    public ResponseEntity<Order> updateOrder(@PathVariable Long orderID, @RequestBody Order updatedOrder){
        try{
            Order order = orderService.updateOrderId(orderID, updatedOrder);
            return ResponseEntity.ok(order);
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // Sletter en order efter ID
    @DeleteMapping("/{orderId}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long orderid){
        try {
            orderService.deleteOrder(orderid);
            return ResponseEntity.noContent().build();

        } catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}
