package com.project.domain.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class ErrorDetails {

    private LocalDateTime timestamp;
    private int statusCode;
    private String message;

    public ErrorDetails(LocalDateTime timestamp, int statusCode, String message) {
        this.timestamp = timestamp;
        this.statusCode = statusCode;
        this.message = message;
    }


}
