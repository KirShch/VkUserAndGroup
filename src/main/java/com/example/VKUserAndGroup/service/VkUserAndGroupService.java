package com.example.VKUserAndGroup.service;

import com.example.VKUserAndGroup.dto.RequestDto;
import com.example.VKUserAndGroup.dto.ResponseDto;
import com.example.VKUserAndGroup.vkApiDto.VkUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
