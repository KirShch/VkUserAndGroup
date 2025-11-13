package com.example.VKUserAndGroup.service;

import com.example.VKUserAndGroup.config.VkProperties;
import com.example.VKUserAndGroup.dto.RequestDto;
import com.example.VKUserAndGroup.dto.ResponseDto;
import com.example.VKUserAndGroup.exception.VkApiException;
import com.example.VKUserAndGroup.exception.VkApiUserNotFoundException;
import com.example.VKUserAndGroup.vkApiDto.VkApiUserGetResponse;
import com.example.VKUserAndGroup.vkApiDto.VkApiGroupMemberResponse;
import com.example.VKUserAndGroup.vkApiDto.VkUser;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@RequiredArgsConstructor
public class VkUserAndGroupService {
    private final ValidRequestDtoService validRequestDtoService;
    private final VkApiService vkApiService;

    public ResponseDto getResponseData(RequestDto requestVkUserDto, String vk_service_token) {
        validRequestDtoService.valid(requestVkUserDto);

        VkUser vkUser = vkApiService.getVkUserByIdAndToken(requestVkUserDto.getUser_id(), vk_service_token);
        boolean member = vkApiService.getMembership(requestVkUserDto.getUser_id(), requestVkUserDto.getGroup_id(), vk_service_token);

        return new ResponseDto(vkUser.getFirstName()
                                    ,vkUser.getLastName()
                                    ,vkUser.getNickname()
                                    ,member);
    }
}
