package com.k8port.microservicescoursemanagement.repository;

import com.k8port.microservicescoursemanagement.model.Transaction;

import java.util.List;

public interface TransactionRepository extends IGenericDao<Transaction> {
    List<Transaction> findAllTransactionsOfUser(Long userId);

    List<Transaction> findAllTransactionsOfCourse(Long courseId);
}
