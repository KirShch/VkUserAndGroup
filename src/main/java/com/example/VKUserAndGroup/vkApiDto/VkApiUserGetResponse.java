package com.example.VKUserAndGroup.vkApiDto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class VkApiUserGetResponse<T> {
    @JsonProperty("response")
    private List<T> response;

    @JsonProperty("error")
    private VkApiError error;
}
