package com.example.VKUserAndGroup.exception;

import org.springframework.http.HttpStatus;

public class EmptyVkGroupIdException extends VkUserAndGroupException{
    public EmptyVkGroupIdException(String message){ super(message, HttpStatus.NOT_ACCEPTABLE); }
}
