package com.k8port.microservicescoursemanagement.repository;

import com.k8port.microservicescoursemanagement.model.Course;

import java.util.List;

public interface CourseRepository extends IGenericDao<Course> {
    List<Course> filterCourses(String text);

    List<Course> filterCourseByIdList(List<Long> idList);
}
