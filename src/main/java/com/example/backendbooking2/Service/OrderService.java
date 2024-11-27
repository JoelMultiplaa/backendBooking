package com.example.backendbooking2.Service;

import com.example.backendbooking2.Entity.Order;
import com.example.backendbooking2.Repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private OrderRepository orderRepository ;

    public OrderService(OrderRepository orderRepository){
        this.orderRepository = orderRepository;
    }

    // Henter alle Order
    public List<Order> getAllOrder(){
        return orderRepository.findAll();
    }

    // Opretter order
    public Order createOrder(Order order){
        return orderRepository.save(order);
    }

    // opdatere order efter id
    public Order updateOrderId(Long orderId, Order updateOrder){
        Order existingOrder = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order with ID " + orderId + " not found"));

        existingOrder.setCustomers(updateOrder.getCustomers());
        existingOrder.setStartDate(updateOrder.getStartDate());
        existingOrder.setLocalTime(updateOrder.getLocalTime());
        existingOrder.setOrderlines(updateOrder.getOrderlines());

        return orderRepository.save(existingOrder);
    }

    // Sletter order efter id
    public void deleteOrder(Long orderId){
        if (!orderRepository.existsById(orderId)){
            throw new RuntimeException("Order by id not found");
        }
        orderRepository.deleteById(orderId);
    }
}
