package com.k8port.microservicelogmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

@EnableDiscoveryClient
@SpringBootApplication
@EnableCassandraRepositories
public class MicroserviceLogManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceLogManagementApplication.class, args);
	}

}
