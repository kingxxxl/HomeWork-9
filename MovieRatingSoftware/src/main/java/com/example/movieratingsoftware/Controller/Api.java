package com.example.movieratingsoftware.Controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Data
public class Api {
    private String message;
    private HttpStatus status;
}