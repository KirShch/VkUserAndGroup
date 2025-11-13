package com.example.VKUserAndGroup.exception;

import org.springframework.http.HttpStatus;

public class VkApiUserNotFoundException extends  VkUserAndGroupException{
    public VkApiUserNotFoundException(String message){ super(message, HttpStatus.BAD_REQUEST); }
}
