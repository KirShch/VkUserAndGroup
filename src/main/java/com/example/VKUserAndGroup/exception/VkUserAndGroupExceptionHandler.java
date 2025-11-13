package com.example.VKUserAndGroup.exception;

import com.example.VKUserAndGroup.dto.ExceptionResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class VkUserAndGroupExceptionHandler {
    private ExceptionResponseDto getResponse(VkUserAndGroupException ex, WebRequest request){
        return new ExceptionResponseDto(
                ex.getStatus().value(),
                ex.getStatus().getReasonPhrase(),
                ex.getMessage(),
                request.getDescription(false).replace("uri=", "")
        );
    }

    /*@ExceptionHandler(EmptyVkUserIdException.class)
    public ResponseEntity<ExceptionResponseDto> handleResourceNotFoundException(EmptyVkUserIdException ex, WebRequest request) {
        return new ResponseEntity<>(getResponse(ex, request), ex.getStatus());
    }

    @ExceptionHandler(EmptyVkGroupIdException.class)
    public ResponseEntity<ExceptionResponseDto> handleResourceNotFoundException(EmptyVkGroupIdException ex, WebRequest request) {
        return new ResponseEntity<>(getResponse(ex, request), ex.getStatus());
    }*/

    @ExceptionHandler(VkUserAndGroupException.class)
    public ResponseEntity<ExceptionResponseDto> handleResourceNotFoundException(VkUserAndGroupException ex, WebRequest request) {
        return new ResponseEntity<>(getResponse(ex, request), ex.getStatus());
    }
}
