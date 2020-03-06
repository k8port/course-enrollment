package com.k8port.microservicecoursemanagement.repository;

import com.k8port.microservicecoursemanagement.model.Course;

import java.util.List;

public interface CourseRepository extends IGenericDao<Course> {
    List<Course> filterCourses(String text);

    List<Course> filterCourseByIdList(List<Long> idList);
}
