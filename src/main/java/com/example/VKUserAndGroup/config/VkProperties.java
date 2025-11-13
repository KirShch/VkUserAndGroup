package com.example.VKUserAndGroup.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "vk")
@Getter
@Setter
public class VkProperties {
    private String vkBaseUrl;
    private String vkApiVersion;
}
