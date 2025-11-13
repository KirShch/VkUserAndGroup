package com.example.VKUserAndGroup.exception;

import org.springframework.http.HttpStatus;

public class EmptyVkUserIdException extends VkUserAndGroupException{
    public EmptyVkUserIdException(String message){ super(message, HttpStatus.NOT_ACCEPTABLE); }
}
