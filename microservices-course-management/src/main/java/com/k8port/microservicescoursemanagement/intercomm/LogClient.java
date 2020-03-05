package com.k8port.microservicescoursemanagement.intercomm;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@FeignClient("log-service")
public interface LogClient {

    @RequestMapping(method = GET, value = "/service/popular", consumes = "application/json")
    List<Long> getPopularCourses();

}
