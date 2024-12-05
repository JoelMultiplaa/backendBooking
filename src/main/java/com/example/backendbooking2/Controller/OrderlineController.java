package com.example.backendbooking2.Controller;

import com.example.backendbooking2.Entity.Orderline;
import com.example.backendbooking2.Service.OrderlineService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/orderlines")
public class OrderlineController {

    private final OrderlineService orderlineService;


    public OrderlineController(OrderlineService orderlineService) {
        this.orderlineService = orderlineService;
    }

    // Opretter en ny orderline
    @PostMapping
    public Orderline createOrderline(@RequestBody Orderline orderline) {
        Orderline createOrderline = orderlineService.createOrderline(orderline);
        return createOrderline;
    }

    // Henter alle orderlines
    @GetMapping
    public List<Orderline> getAllOrderlines() {
        return orderlineService.getAllOrderline();
    }

    // Henter en orderline ved ID
    @GetMapping("/{id}")
    public Orderline getOrderlineById(@PathVariable Long id) {
        return orderlineService.getOrderlineById(id).orElseThrow(() -> new RuntimeException("Orderline not found"));
    }

    // Opdaterer en orderline
    @PutMapping("/{id}")
    public Orderline updateOrderline(@PathVariable Long id, @RequestBody Orderline orderline) {
        return orderlineService.updateOrderline(id, orderline);
    }

    // Sletter en orderline
    @DeleteMapping("/{id}")
    public void deleteOrderline(@PathVariable Long id) {
        orderlineService.deleteOrderline(id);
    }
}
