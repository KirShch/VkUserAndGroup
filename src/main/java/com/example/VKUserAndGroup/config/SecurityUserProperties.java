package com.example.VKUserAndGroup.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "security.user")
@Getter
@Setter
public class SecurityUserProperties {
    private String name;
    private String password;
    private String role;
}
