package com.k8port.microservicecoursemanagement.intercomm;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@FeignClient("user-service")
public interface UserClient {

    @RequestMapping(method = POST, value = "/service/names", consumes = "application/json")
    List<String> getUsers(@RequestBody List<Long> userIdList);

}
