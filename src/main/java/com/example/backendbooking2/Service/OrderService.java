package com.example.backendbooking2.Service;

import com.example.backendbooking2.Entity.Customer;
import com.example.backendbooking2.Entity.Order;
import com.example.backendbooking2.Entity.Orderline;
import com.example.backendbooking2.Repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
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
    // Tjekker om den ønskede tid er tilgængelig
    public boolean isTimeAvailable(LocalDate startDate, LocalTime localTime){
        return !orderRepository.existsByStartDateAndLocalTime(startDate, localTime);
    }

    // Opretter order
    public Order createOrder(Order order){
        if (isTimeAvailable(order.getStartDate(), order.getLocalTime())){
            throw new RuntimeException("The selected time slot is already booked.");
        }
        // Sørg for, at Orderlines og Customers også er sat korrekt
        for (Orderline orderline: order.getOrderlines()){
            orderline.setOrder(order); // Sætter ordren for hver orderline
        }


        for (Customer customer: order.getCustomers()){
            customer.setOrder(order); // Sætter ordren for hver customer
        }
        return orderRepository.save(order);
    }

    // opdatere order efter id
    public Order updateOrderId(Long orderId, Order updateOrder){
        Order existingOrder = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order with ID " + orderId + " not found"));

        existingOrder.setStartDate(updateOrder.getStartDate());
        existingOrder.setLocalTime(updateOrder.getLocalTime());

        // Opdaterer ordrelinjer
        existingOrder.getOrderlines().clear(); // Fjern eksisterende orderlines
        existingOrder.getOrderlines().addAll(updateOrder.getOrderlines()); // Tilføj nye orderlines

        for (Orderline orderline: updateOrder.getOrderlines()){
            orderline.setOrder(existingOrder); // Sæt relationen til den eksisterende ordre
        }

        // Opdaterer kunder
        existingOrder.getCustomers().clear();// Fjern eksisterende kunder
        existingOrder.getCustomers().addAll(updateOrder.getCustomers()); // Tilføj nye kunder

        for (Customer customer: updateOrder.getCustomers()){
            customer.setOrder(existingOrder); // Sæt relationen til den eksisterende ordre
        }

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
