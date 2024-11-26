package com.example.backendbooking2.util;

public class GenericResponse<T> {
    int status;
    String message;
    T data;

    public GenericResponse(int status, String message, T data)
    {
        this.status=status;
        this.message=message;
        this.data=data;
    }
}
