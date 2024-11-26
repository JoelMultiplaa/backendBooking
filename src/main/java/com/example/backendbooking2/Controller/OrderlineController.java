package com.example.backendbooking2.Controller;

import com.example.backendbooking2.Entity.Orderline;
import com.example.backendbooking2.Service.OrderlineService;
import com.example.backendbooking2.util.GenericResponse;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@NoArgsConstructor
@RestController
@CrossOrigin("*")
@RequestMapping("/api/Orderline")
public class OrderlineController {
    private OrderlineService orderlineService;


    @GetMapping("/{id}")
    public ResponseEntity<GenericResponse<?>> getOrderLineById(@PathVariable Long orderLineId) {
        return orderlineService.getOrderlineById(orderLineId)
                .<ResponseEntity<GenericResponse<?>>>map(orderline -> ResponseEntity.ok(new GenericResponse<>(200, "Orderline found", orderline)))
                .orElseGet(() -> ResponseEntity
                        .status(HttpStatus.NO_CONTENT)
                        .body(new GenericResponse<>(204, "No Bookings Found", null)));
    }








}
