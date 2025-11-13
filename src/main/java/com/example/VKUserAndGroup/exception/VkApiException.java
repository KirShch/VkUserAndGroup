package com.example.VKUserAndGroup.exception;

import org.springframework.http.HttpStatus;

public class VkApiException extends  VkUserAndGroupException{
    public VkApiException(String message){ super(message, HttpStatus.BAD_REQUEST); }
}
