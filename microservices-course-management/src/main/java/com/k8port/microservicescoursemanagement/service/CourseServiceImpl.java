package com.k8port.microservicescoursemanagement.service;

import com.k8port.microservicescoursemanagement.model.Course;
import com.k8port.microservicescoursemanagement.model.Transaction;
import com.k8port.microservicescoursemanagement.repository.CourseRepository;
import com.k8port.microservicescoursemanagement.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private TransactionRepository transactionRepository;


    @Override
    public List<Course> allCourses() {
        return courseRepository.findAll();
    }

    @Override
    public List<Course> filterCourses(final String content) {
        return courseRepository.filterCourses(content);
    }

    @Override
    public List<Transaction> filterTransactionsOfUser(final Long userId) {
        return transactionRepository.findAllTransactionsOfUser(userId);
    }

    @Override
    public List<Transaction> filterTransactionsOfCourse(final Long courseId) {
        return transactionRepository.findAllTransactionsOfCourse(courseId);
    }

    @Override
    public void saveTransaction(final Transaction transaction) {
        transactionRepository.save(transaction);
    }

    @Override
    public Course findCourseById(Long courseId) {
        return courseRepository.find(courseId);
    }

    @Override
    public List<Course> filterCoursesByIdList(List<Long> popularIdList) {
        return courseRepository.filterCourseByIdList(popularIdList);
    }
}
