package com.example.backendbooking2.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "available_time")
public class AvailableTime {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long availableTimeId;

    @Column(name = "date")
    private LocalDate  availableDate;


    @Column(name = "startTime")
    private LocalTime startTime;

    @Column(name = "endTime")
    private LocalTime endTime;

    @Column(name = "booked")
    private boolean isBooked;

    @OneToOne(mappedBy = "available_time", fetch = FetchType.EAGER)
    private Order order;
}
