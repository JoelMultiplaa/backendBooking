package com.example.backendbooking2.Service;

import com.example.backendbooking2.Entity.Customer;
import com.example.backendbooking2.Entity.Order;
import com.example.backendbooking2.Entity.Orderline;
import com.example.backendbooking2.Repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository ;


    public OrderService(OrderRepository orderRepository){
        this.orderRepository = orderRepository;
    }

    // Henter alle Order
    public List<Order> getAllOrder(){
        return orderRepository.findAll();
    }

    // Opretter en ny ordre
    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }


    // opdatere order efter id
    public Order updateOrderId(Long orderId, Order updateOrder) {
        Order existOrder = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Unavailable to updtae order id " + orderId));

        existOrder.setDate(updateOrder.getDate());
       // existOrder.setTime(updateOrder.getTime());
        existOrder.setWorkshopLocation(updateOrder.getWorkshopLocation());

        return orderRepository.save(existOrder);
    }


    // Sletter order efter id
    public void deleteOrder(Long orderId){
        if (!orderRepository.existsById(orderId)){
            throw new RuntimeException("Order with ID " + orderId + " not found");
        }
        orderRepository.deleteById(orderId);
    }
}
