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
public class AvailableTime {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long availableTimeId;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    @DateTimeFormat(pattern = "HH:mm:ss")
    private LocalTime localTime;


}
