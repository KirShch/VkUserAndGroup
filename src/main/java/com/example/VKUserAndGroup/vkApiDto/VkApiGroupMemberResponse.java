package com.example.VKUserAndGroup.vkApiDto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VkApiGroupMemberResponse {
    @JsonProperty("response")
    private Integer response;

    @JsonProperty("error")
    private VkApiError error;
}
