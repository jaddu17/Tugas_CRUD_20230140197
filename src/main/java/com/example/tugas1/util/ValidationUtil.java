package com.example.tugas1.util;

import org.springframework.stereotype.Component;

@Component
public class ValidationUtil {
    public void validate(Object request) {
        if (request == null) {
            throw new RuntimeException("Request cannot be null");
        }
    }
}
