package com.example.VKUserAndGroup.service;

import com.example.VKUserAndGroup.config.VkProperties;
import com.example.VKUserAndGroup.exception.VkApiException;
import com.example.VKUserAndGroup.exception.VkApiUserNotFoundException;
import com.example.VKUserAndGroup.vkApiDto.VkApiGroupMemberResponse;
import com.example.VKUserAndGroup.vkApiDto.VkApiUserGetResponse;
import com.example.VKUserAndGroup.vkApiDto.VkUser;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@RequiredArgsConstructor
public class VkApiService {
    private final WebClient vkWebClient;
    private final VkProperties vkProperties;
    private final ObjectMapper objectMapper;

    @Cacheable(value = "userName", key = "#userId")
    public VkUser getVkUserByIdAndToken(String userId, String vkServiceToken){
        String uriUser = usersGetUri(userId
                                    ,vkServiceToken
                                    ,vkProperties.getVkApiVersion());
        return getVkUser(uriUser);
    }

    @Cacheable(value = "groupMembership", key = "#userId + '-' + #groupId")
    public boolean getMembership(String userId, String groupId, String vkServiceToken){
        String uriM = groupMembershipUri(userId
                                        ,groupId
                                        ,vkServiceToken
                                        ,vkProperties.getVkApiVersion());

        return member(uriM);
    }

    public String usersGetUri(String userId, String vk_service_token, String vkApiVersion){
        return UriComponentsBuilder.fromPath("/users.get")
                .queryParam("user_ids", userId)
                .queryParam("fields", "first_name,last_name,nickname")
                .queryParam("access_token", vk_service_token)
                .queryParam("v", vkApiVersion)
                .build()
                .toUriString();
    }

    public String groupMembershipUri(String userId, String groupId, String vk_service_token, String vkApiVersion){
        return UriComponentsBuilder.fromPath("/groups.isMember")
                .queryParam("user_id", userId)
                .queryParam("group_id", groupId)
                .queryParam("access_token", vk_service_token)
                .queryParam("v", vkApiVersion)
                .build()
                .toUriString();
    }

    public boolean member(String uri){
        VkApiGroupMemberResponse vkGroupMemberResponse = getVkGroupMemberResponse(uri);
        if (vkGroupMemberResponse.getError() != null)
            throw new VkApiException("VK API Error: " + vkGroupMemberResponse.getError().getErrorMessage());

        return member(getVkGroupMemberResponse(uri));
    }

    public boolean member(VkApiGroupMemberResponse vkApiGroupMemberResponse){
        if (vkApiGroupMemberResponse.getError() != null)
            throw new VkApiException("VK API Error: " + vkApiGroupMemberResponse.getError().getErrorMessage());

        return vkApiGroupMemberResponse.getResponse() == 1;
    }

    public VkApiGroupMemberResponse getVkGroupMemberResponse(String uri){
        return vkWebClient.get()
                .uri(uri)
                .retrieve()
                .bodyToMono(VkApiGroupMemberResponse.class)
                .blockOptional().orElseThrow(() -> new VkApiException("VK API null response (Membership to group)"));
    }


    public VkUser getVkUser(String uri){
        return getVkUser(getVkApiResponse(uri));
    }

    public VkUser getVkUser(VkApiUserGetResponse vkApiResponse){
        if (vkApiResponse.getError() != null)
            throw new VkApiException("VK API Error: " + vkApiResponse.getError().getErrorMessage());

        if (vkApiResponse.getResponse() == null || vkApiResponse.getResponse().isEmpty()) {
            throw new VkApiUserNotFoundException("User not found");
        }

        return objectMapper.convertValue(vkApiResponse.getResponse().getFirst(), VkUser.class);
    }

    public VkApiUserGetResponse getVkApiResponse(String uri){
        return vkWebClient.get()
                .uri(uri)
                .retrieve()
                .bodyToMono(VkApiUserGetResponse.class)
                .blockOptional().orElseThrow(() -> new VkApiException("VK API null response (User info)"));
    }

}
