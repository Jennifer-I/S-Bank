package com.jennifer.sbank.utils;

import lombok.*;
import org.springframework.http.HttpStatusCode;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ApiResponse<T> {
    private T data;
    private String message;
    private boolean status;


    public boolean getStatus() {
        return status;
    }
}
