package com.k8port.microservicecoursemanagement.repository;

import com.k8port.microservicecoursemanagement.model.IModel;
import org.hibernate.Session;

import java.util.List;

public interface IGenericDao<T extends IModel> {
    T find(Long id);

    List<T> findAll();

    void save(T entity);

    T update(T entity);

    void delete(Long id);

    Session getSession();
}
