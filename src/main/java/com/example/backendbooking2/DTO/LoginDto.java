package com.example.backendbooking2.DTO;


import com.example.backendbooking2.Entity.Category;
import com.example.backendbooking2.Entity.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LoginDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private String email;

    private String password;

}
