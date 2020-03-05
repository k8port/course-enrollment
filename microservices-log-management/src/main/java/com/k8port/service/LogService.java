package com.k8port.service;

import com.k8port.model.Log;
import com.k8port.model.Summary;

import java.util.List;

public interface LogService {

    Summary findSummaryByCourseId(Long courseId);

    Log saveOrUpdate(Log log);

    Summary saveOrUpdate(Summary summary);

    List<Summary> findPopularCourses();

}
