package com.example.VKUserAndGroup.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@RequiredArgsConstructor
public class VkApiConfig {

    private final VkProperties vkProperties;

    @Bean
    public WebClient vkWebClient() {
        return WebClient.builder()
                .baseUrl(vkProperties.getVkBaseUrl())
                .build();
    }
}
