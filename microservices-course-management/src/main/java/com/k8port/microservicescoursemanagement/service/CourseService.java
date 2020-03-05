package com.k8port.microservicescoursemanagement.service;

import com.k8port.microservicescoursemanagement.model.Course;
import com.k8port.microservicescoursemanagement.model.Transaction;

import java.util.List;

public interface CourseService {
    List<Course> allCourses();

    List<Course> filterCourses(String content);

    List<Transaction> filterTransactionsOfUser(Long userId);

    List<Transaction> filterTransactionsOfCourse(Long courseId);

    void saveTransaction(Transaction transaction);

    Course findCourseById(Long courseId);

    List<Course> filterCoursesByIdList(List<Long> popularIdList);
}
