package com.cts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@EnableDiscoveryClient
@Slf4j
public class VotingSystemAuthServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(VotingSystemAuthServiceApplication.class, args);
		log.info("server started at 8100");
	}

}
