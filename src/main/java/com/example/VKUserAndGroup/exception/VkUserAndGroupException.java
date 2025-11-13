package com.example.VKUserAndGroup.exception;

import org.springframework.http.HttpStatus;

public class VkUserAndGroupException extends RuntimeException{
    private final HttpStatus status;

    public VkUserAndGroupException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
