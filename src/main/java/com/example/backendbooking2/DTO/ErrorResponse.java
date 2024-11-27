package com.example.backendbooking2.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter

public class ErrorResponse {
    private String message;

    public ErrorResponse(String message){
        this.message = message;
    }
}
