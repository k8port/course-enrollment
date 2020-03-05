package com.k8port.microservices_eurekadiscoveryservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class MicroservicesEurekaDiscoveryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroservicesEurekaDiscoveryServiceApplication.class, args);
	}

}
