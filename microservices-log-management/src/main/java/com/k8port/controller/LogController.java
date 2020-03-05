package com.k8port.controller;

import com.k8port.model.Log;
import com.k8port.model.Summary;
import com.k8port.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

import static java.time.LocalDateTime.now;
import static java.util.stream.Collectors.toList;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/service")
public class LogController {

    @Autowired
    private LogService logService;

    @PostMapping("/summary")
    public ResponseEntity<?> getSummaryOfCourse(@RequestBody Long courseId) {
        return new ResponseEntity<>(logService.findSummaryByCourseId(courseId), OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> saveLog(@RequestBody Log log) {
        log.setLogDate(now());
        logService.saveOrUpdate(log);
        return ok(log);
    }

    @GetMapping("/popular")
    public ResponseEntity<?> findPopularCourses() {
        List<Long> idList = null;
        List<Summary> popularCourses = logService.findPopularCourses();
        if (popularCourses != null && !popularCourses.isEmpty()) {
            idList = popularCourses.parallelStream().map(Summary::getCourseId)
                    .collect(toList());
        }
        return ok(Objects.requireNonNull(idList));
    }

}
