package com.example.VKUserAndGroup.dto;

public record ExceptionResponseDto(int status, String exception, String message, String path) {
}
