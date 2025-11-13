package com.example.VKUserAndGroup.service;

import com.example.VKUserAndGroup.config.VkProperties;
import com.example.VKUserAndGroup.dto.RequestDto;
import com.example.VKUserAndGroup.dto.ResponseDto;
import com.example.VKUserAndGroup.exception.*;
import com.example.VKUserAndGroup.vkApiDto.VkApiError;
import com.example.VKUserAndGroup.vkApiDto.VkApiGroupMemberResponse;
import com.example.VKUserAndGroup.vkApiDto.VkApiUserGetResponse;
import com.example.VKUserAndGroup.vkApiDto.VkUser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

class VkUserAndGroupServiceTest {

    private VkUserAndGroupService vkUserAndGroupService;
    @Mock
    private WebClient vkWebClient;

    @BeforeEach
    void init(){
        VkProperties vkProperties = new VkProperties();
        vkProperties.setVkApiVersion("5.199");
        vkProperties.setVkBaseUrl("https://api.vk.com/method");
        ObjectMapper objectMapper = new ObjectMapper();
        VkApiService vkApiService = new VkApiService(vkWebClient, vkProperties, objectMapper);
        vkUserAndGroupService = new VkUserAndGroupService(new ValidRequestDtoService(), vkApiService);
    }

    @Test
    void getResponseDataValidRequestDtoEmptyUserId() {
        RequestDto requestDto = new RequestDto();
        requestDto.setUser_id("");
        requestDto.setGroup_id("1");
        String vk_service_token = "111";
        assertThrows(EmptyVkUserIdException.class, () -> vkUserAndGroupService.getResponseData(requestDto, vk_service_token));
    }

    @Test
    void getResponseDataValidRequestDtoNullUserId() {
        RequestDto requestDto = new RequestDto();
        requestDto.setUser_id(null);
        requestDto.setGroup_id("1");
        String vk_service_token = "111";
        assertThrows(EmptyVkUserIdException.class, () -> vkUserAndGroupService.getResponseData(requestDto, vk_service_token));
    }

    @Test
    void getResponseDataValidRequestDtoEmptyGroupId() {
        RequestDto requestDto = new RequestDto();
        requestDto.setUser_id("1");
        requestDto.setGroup_id("");
        String vk_service_token = "111";
        assertThrows(EmptyVkGroupIdException.class, () -> vkUserAndGroupService.getResponseData(requestDto, vk_service_token));
    }

    @Test
    void getResponseDataValidRequestDtoNullGroupId() {
        RequestDto requestDto = new RequestDto();
        requestDto.setUser_id("1");
        requestDto.setGroup_id(null);
        String vk_service_token = "111";
        assertThrows(EmptyVkGroupIdException.class, () -> vkUserAndGroupService.getResponseData(requestDto, vk_service_token));
    }
}