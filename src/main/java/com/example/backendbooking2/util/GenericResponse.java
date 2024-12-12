package com.example.backendbooking2.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GenericResponse<T> {
    int status;
    String message;
    T data;
    public GenericResponse(String message, T data) {
        this.message = message;
        this.data = data;
    }
}
