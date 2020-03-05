package com.k8port.microservicescoursemanagement.controller;

import com.k8port.microservicescoursemanagement.intercomm.LogClient;
import com.k8port.microservicescoursemanagement.intercomm.UserClient;
import com.k8port.microservicescoursemanagement.model.Transaction;
import com.k8port.microservicescoursemanagement.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.core.env.Environment;

import java.util.List;

import static java.time.LocalDateTime.now;
import static java.util.stream.Collectors.toList;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.ResponseEntity.notFound;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/service")
public class CourseController {

    @Autowired
    private UserClient userClient;

    @Autowired
    private LogClient logClient;

    @Autowired
    private CourseService courseService;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private Environment environment;

    @Value("$(spring.application.name")
    private String serviceId;

    @GetMapping("/service/port")
    public String getPort() {
        return "Service working at port: " + environment.getProperty("local.server.port");
    }

    @GetMapping("/service/instances")
    public ResponseEntity<?> getInstances() {
        return ok(discoveryClient.getInstances(serviceId));
    }

    @PostMapping("/user")
    public ResponseEntity<?> filterTransactions(@RequestBody Long userId) {
        return new ResponseEntity<>(courseService.filterTransactionsOfUser(userId), OK);
    }

    @GetMapping("/popular")
    public ResponseEntity<?> popularCourses() {
        List<Long> popularIdList = logClient.getPopularCourses();
        if (popularIdList == null || popularIdList.isEmpty()) {
            return notFound().build();
        }
        return new ResponseEntity<>(courseService.filterCoursesByIdList(popularIdList), OK);
    }

    @PostMapping("/")
    public ResponseEntity<?> allCourses() {
        return new ResponseEntity<>(courseService.allCourses(), OK);
    }

    @PostMapping("/filter")
    public ResponseEntity<?> filterCourses(@RequestBody String text) {
        return new ResponseEntity<>(courseService.filterCourses(text), OK);
    }

    @PostMapping("/enroll")
    public ResponseEntity<?> saveTransaction(@RequestBody Transaction transaction) {
        transaction.setDateOfIssue(now());
        transaction.setCourse(courseService.findCourseById(transaction.getCourse().getId()));
        courseService.saveTransaction(transaction);
        return ok(transaction);
    }

    @PostMapping("/students")
    public ResponseEntity<?> findCourseStudents(@RequestBody Long courseId) {
        List<Transaction> transactionList = courseService.filterTransactionsOfCourse(courseId);
        if (transactionList != null  && !transactionList.isEmpty()) {
            List<Long> userIdList = (transactionList.parallelStream().map(Transaction::getUserId)
                    .collect(toList()));
            List<String> students = userClient.getUsers(userIdList);
            return ok(students);
        }
        return notFound().build();
    }
}
