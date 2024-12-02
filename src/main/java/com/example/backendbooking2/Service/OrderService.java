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

    private final AvailableTimeService availableTimeService;

    public OrderService(OrderRepository orderRepository, AvailableTimeService availableTimeService){
        this.orderRepository = orderRepository;
        this.availableTimeService = availableTimeService;
    }

    // Henter alle Order
    public List<Order> getAllOrder(){
        return orderRepository.findAll();
    }

    // Opretter en ny ordre
    public Order createOrder(Order order) {
        if (!availableTimeService.isTimeAvailable(order.getStartDate())) {
            throw new RuntimeException("The selected time slot is already booked.");
        }

        // Sørg for, at Orderlines er sat korrekt
        for (Orderline orderline : order.getOrderlines()) {
            orderline.setOrder(order); // Sætter ordren for hver orderline
        }

        // Sørg for, at kunden er sat korrekt (hvis der kun er én)
        Customer customer = order.getCustomer(); // Hvis der kun er én kunde
        if (customer != null) {
            customer.setOrder(order); // Sætter ordren for kunden
        }

        return orderRepository.save(order);
    }


    // opdatere order efter id
    public Order updateOrderId(Long orderId, Order updateOrder) {
        Order existingOrder = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order with ID " + orderId + " not found"));

        existingOrder.setStartDate(updateOrder.getStartDate());
        existingOrder.setLocalTime(updateOrder.getLocalTime());

        // Opdaterer ordrelinjer
        existingOrder.getOrderlines().clear(); // Fjern eksisterende orderlines
        for (Orderline orderline : updateOrder.getOrderlines()) {
            orderline.setOrder(existingOrder); // Sæt relationen til den eksisterende ordre
        }
        existingOrder.getOrderlines().addAll(updateOrder.getOrderlines()); // Tilføj nye orderlines

        // Opdaterer kunden (hvis der kun er én)
        existingOrder.setCustomer(updateOrder.getCustomer()); // Opdater eksisterende kunde

        return orderRepository.save(existingOrder);
    }


    // Sletter order efter id
    public void deleteOrder(Long orderId){
        if (!orderRepository.existsById(orderId)){
            throw new RuntimeException("Order with ID " + orderId + " not found");
        }
        orderRepository.deleteById(orderId);
    }
}
