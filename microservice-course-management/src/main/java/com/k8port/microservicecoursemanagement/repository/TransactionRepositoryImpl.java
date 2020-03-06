package com.k8port.microservicecoursemanagement.repository;

import com.k8port.microservicecoursemanagement.model.Transaction;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import java.util.List;

@Transactional
@Repository
public class TransactionRepositoryImpl extends AbstractGenericDao<Transaction> implements TransactionRepository {

    @Override
    public List<Transaction> findAllTransactionsOfUser(final Long userId) {
        Query query = em.createQuery("Select t from Transaction t where t.userId = :pUserId");
        query.setParameter("pUserId", userId);
        return query.getResultList();
    }

    @Override
    public List<Transaction> findAllTransactionsOfCourse(final Long courseId) {
        Query query = em.createQuery("Select t from Transaction t where t.course.id = :pCourseId");
        query.setParameter("pCourseId", courseId);
        return query.getResultList();
    }

}
