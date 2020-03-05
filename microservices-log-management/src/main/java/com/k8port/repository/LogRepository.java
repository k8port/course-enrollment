package com.k8port.repository;

import com.k8port.model.Log;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public interface LogRepository extends CrudRepository<Log, UUID> {
}
