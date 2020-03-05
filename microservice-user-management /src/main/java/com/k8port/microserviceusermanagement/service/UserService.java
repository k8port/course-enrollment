package com.k8port.microserviceusermanagement.service;

import com.k8port.microserviceusermanagement.model.User;

import java.util.List;

public interface UserService {
    User save(User user);

    User findByUsername(String username);

    List<String> findUsers(List<Long> idList);
}
