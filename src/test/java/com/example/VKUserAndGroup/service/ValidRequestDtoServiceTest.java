package com.example.VKUserAndGroup.service;

import com.example.VKUserAndGroup.exception.EmptyVkGroupIdException;
import com.example.VKUserAndGroup.exception.EmptyVkUserIdException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class ValidRequestDtoServiceTest {

    private ValidRequestDtoService validRequestDtoService;

    @BeforeEach
    void init(){
        validRequestDtoService = new ValidRequestDtoService();
    }

    @Test
    void validUserIdEmpty() {
        assertThrows(EmptyVkUserIdException.class, () -> validRequestDtoService.validUserId(""));
    }

    @Test
    void validUserIdNull() {
        assertThrows(EmptyVkUserIdException.class, () -> validRequestDtoService.validUserId(null));
    }

    @Test
    void validGroupIdEmpty() {
        assertThrows(EmptyVkGroupIdException.class, () -> validRequestDtoService.validGroupId(""));
    }

    @Test
    void validGroupIdNull() {
        assertThrows(EmptyVkGroupIdException.class, () -> validRequestDtoService.validGroupId(null));
    }
}