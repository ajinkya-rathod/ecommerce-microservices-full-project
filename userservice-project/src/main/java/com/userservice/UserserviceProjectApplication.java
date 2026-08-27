package com.userservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class UserserviceProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserserviceProjectApplication.class, args);
	}

}
