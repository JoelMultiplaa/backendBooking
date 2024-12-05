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
    public Optional<Orderline> createOrderline(Orderline orderlineId){
       Optional<Orderline> createOrderline = orderlineRepository.findById(orderlineId);
        if (createOrderline.isPresent()) {
            Orderline orderline = createOrderline.get();
            return Optional.of(orderlineRepository.save(orderline));
        } else {
            return Optional.empty();
        }
    }

    //Henter alle order linjerne
    public List<Orderline> getAllOrderline(){
        List<Orderline> ordersList = orderlineRepository.findAll();
        return ordersList;
    }

    //Henter alle order linjerne af id
    public Optional<Orderline> getOrderlineById(Long orderlineId){
        return Optional.ofNullable(orderlineRepository.findById(orderlineId)
                .orElseThrow(() -> new RuntimeException("Orderline not found")));
    }


    // Opdatere order linjen
    public Orderline updateOrderline(Long orderlineId, Orderline updatedOrderline){
      Orderline existOrderLine = orderlineRepository.findById(orderlineId)
              .orElseThrow(() -> new RuntimeException("Orderline not found" + orderlineId));

      existOrderLine.setQuantity(updatedOrderline.getQuantity());
      existOrderLine.setOrder(updatedOrderline.getOrder());
      existOrderLine.setProducts(updatedOrderline.getProducts());

      return orderlineRepository.save(existOrderLine);

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
