package com.k8port;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

@EnableDiscoveryClient
@SpringBootApplication
@EnableCassandraRepositories
public class MicroservicesLogManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroservicesLogManagementApplication.class, args);
	}

}
