package com.k8port.microservicelogmanagement.repository;

import com.k8port.microservicelogmanagement.model.Log;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public interface LogRepository extends CrudRepository<Log, UUID> {
}
