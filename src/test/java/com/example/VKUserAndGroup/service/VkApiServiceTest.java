package com.example.VKUserAndGroup.service;

import com.example.VKUserAndGroup.config.VkProperties;
import com.example.VKUserAndGroup.exception.VkApiException;
import com.example.VKUserAndGroup.exception.VkApiUserNotFoundException;
import com.example.VKUserAndGroup.vkApiDto.VkApiError;
import com.example.VKUserAndGroup.vkApiDto.VkApiGroupMemberResponse;
import com.example.VKUserAndGroup.vkApiDto.VkApiUserGetResponse;
import com.example.VKUserAndGroup.vkApiDto.VkUser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

class VkApiServiceTest {

    private VkApiService vkApiService;
    @Mock
    private WebClient vkWebClient;

    @BeforeEach
    void init(){
        VkProperties vkProperties = new VkProperties();
        vkProperties.setVkApiVersion("5.199");
        vkProperties.setVkBaseUrl("https://api.vk.com/method");
        ObjectMapper objectMapper = new ObjectMapper();
        vkApiService = new VkApiService(vkWebClient, vkProperties, objectMapper);
    }


    @Test
    void memberDeserializationCheck() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String json = """
                { "response": 1 }
                """;
        VkApiGroupMemberResponse memberResponse = objectMapper.readValue(json, VkApiGroupMemberResponse.class);
        assertEquals(1, memberResponse.getResponse());
    }

    @Test
    void memberNotEmptyError() {
        VkApiGroupMemberResponse vkResponse = new VkApiGroupMemberResponse();
        vkResponse.setError(new VkApiError());
        assertThrows(VkApiException.class, () -> vkApiService.member(vkResponse));
    }

    @Test
    void getVkGroupMemberResponseDeserializationCheck() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String json = """
                { "response": [ { "id": 743784474, "first_name": "Персик", "last_name": "Рыжий", "nickname": "nick" } ] }
                """;
        VkApiUserGetResponse response = objectMapper.readValue(json, VkApiUserGetResponse.class);
        var userList = response.getResponse();
        assertEquals(1, userList.size());
        VkUser user = objectMapper.convertValue(userList.getFirst(), VkUser.class);
        assertEquals("Персик", user.getFirstName());
        assertEquals("Рыжий", user.getLastName());
        assertEquals("nick", user.getNickname());
    }

    @Test
    void getVkUserNotEmptyError() {
        VkApiUserGetResponse<VkUser> vkResponse = new VkApiUserGetResponse<>();
        vkResponse.setError(new VkApiError());
        assertThrows(VkApiException.class, () -> vkApiService.getVkUser(vkResponse));
    }

    @Test
    void getVkUserNullErrorAndListOfVkUsers() {
        VkApiUserGetResponse<VkUser> vkResponse = new VkApiUserGetResponse<>();
        assertThrows(VkApiUserNotFoundException.class, () -> vkApiService.getVkUser(vkResponse));
    }

    @Test
    void getVkUserNullErrorAndEmptyListOfVkUsers() {
        VkApiUserGetResponse<VkUser> vkResponse = new VkApiUserGetResponse<>();
        vkResponse.setResponse(new LinkedList<VkUser>());
        assertThrows(VkApiUserNotFoundException.class, () -> vkApiService.getVkUser(vkResponse));
    }

    @Test
    void getVkUser() {
        VkApiUserGetResponse<VkUser> vkResponse = new VkApiUserGetResponse<>();
        vkResponse.setResponse(new LinkedList<VkUser>());
        VkUser user = new VkUser();
        user.setId(1L);
        user.setFirstName("Ivan");
        user.setLastName("Ivanov");
        user.setNickname("Ivanovich");
        vkResponse.getResponse().add(user);
        assertEquals(user, vkResponse.getResponse().getFirst());
    }
}