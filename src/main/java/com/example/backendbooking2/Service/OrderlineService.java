package com.example.backendbooking2.Service;

import com.example.backendbooking2.Entity.Orderline;
import com.example.backendbooking2.Repository.OrderlineRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderlineService {

    private final OrderlineRepository orderlineRepository;

    public OrderlineService(OrderlineRepository orderlineRepository){
    this.orderlineRepository = orderlineRepository;
    }

    // Opretter orderline
    public Orderline createOrderline(Orderline orderline){
        return Optional.ofNullable(orderline)
                .map(orderlineRepository::save)
                .orElseThrow( () -> new RuntimeException("Invalid Orderline data"));
    }

    //Henter alle order linjerne
    public Optional<List<Orderline>> getAllOrderline(){
        List<Orderline> ordersList = orderlineRepository.findAll();
        return Optional.of(ordersList.stream().toList());
    }

    //Henter alle order linjerne af id
    public Optional<Orderline> getOrderlineById(Long orderlineId){
        return Optional.ofNullable(orderlineRepository.findById(orderlineId)
                .orElseThrow(() -> new RuntimeException("Orderline not found")));
    }


    // Opdatere order linjen
    public Optional<Orderline> updateOrderline(Long orderlineId, Orderline updatedOrderline){
        return Optional.of(orderlineRepository.findById(orderlineId)
                .map(existingOrderline -> {
                    existingOrderline.setQuantity(updatedOrderline.getQuantity());
                    existingOrderline.setService(updatedOrderline.getService());
                    existingOrderline.setOrder(updatedOrderline.getOrder());
                    return orderlineRepository.save(existingOrderline);
                })
                .orElseThrow(() -> new RuntimeException("Orderline not found")));
    }

    public void deleteOrderline(Long orderlineId)
    {
        Optional<Orderline> orderlineById=orderlineRepository.findById(orderlineId);
        if (orderlineById.isPresent())
        {
            orderlineRepository.deleteById(orderlineId);
        }
    }

}
