package com.example.VKUserAndGroup.controller;

import com.example.VKUserAndGroup.dto.RequestDto;
import com.example.VKUserAndGroup.dto.ResponseDto;
import com.example.VKUserAndGroup.service.VkUserAndGroupService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class VkUserAndGroupController {
    private final VkUserAndGroupService vkUserAndGroupService;

    @Operation(summary = "Get VK user full name and group membership",
            description = "Return last name, first name, middle name and boolean value of membership to group")
    @ApiResponse(responseCode = "200", description = "Data obtained")
    @PostMapping("/info")
    public ResponseDto info(
            @Parameter(description = "Request DTO", required = true)
            @RequestBody RequestDto requestVkUserDto,
            @RequestHeader("vk_service_token") String vk_service_token){
        return vkUserAndGroupService.getResponseData(requestVkUserDto, vk_service_token);
    }
}
