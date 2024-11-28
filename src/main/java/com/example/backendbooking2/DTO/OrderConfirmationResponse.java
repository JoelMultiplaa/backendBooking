package com.example.backendbooking2.DTO;

import com.example.backendbooking2.Entity.Customer;
import com.example.backendbooking2.Entity.Orderline;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter

public class OrderConfirmationResponse {

    private LocalDate startDate;
    private LocalTime localTime;
    private List<Orderline> orderlies;
    private List<Customer> customers;
   

    public OrderConfirmationResponse(LocalDate startDate, LocalTime localTime, List<Orderline> orderlies, List<Customer> customers ) {
        this.startDate = startDate;
        this.localTime = localTime;
        this.orderlies = orderlies;
        this.customers = customers;
        
        
    }

    public OrderConfirmationResponse(Object startDate, Object localTime, Object orderlies, Object customers, Object o, String s) {
    }
}
