package com.example.demo.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter @Setter
@AllArgsConstructor
public class AuthErrorMessage {
    private final int statusCode;
    private final Date timestamp;
    private final String message;
    private final String description;

    @Override
    public String toString() {
        return "AuthErrorMessage{" +
                "statusCode=" + statusCode +
                ", timestamp=" + timestamp +
                ", message='" + message + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}