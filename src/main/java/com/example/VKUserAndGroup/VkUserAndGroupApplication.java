package com.example.VKUserAndGroup;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class VkUserAndGroupApplication {

	public static void main(String[] args) {
		SpringApplication.run(VkUserAndGroupApplication.class, args);
	}

}
