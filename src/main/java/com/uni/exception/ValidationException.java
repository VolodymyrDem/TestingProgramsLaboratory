package com.uni.exception;

import lombok.Getter;

@Getter
public class ValidationException extends Exception {
    private final String recommendation;

    public ValidationException(String message, String recommendation) {
        super(message);
        this.recommendation = recommendation;
    }
}
