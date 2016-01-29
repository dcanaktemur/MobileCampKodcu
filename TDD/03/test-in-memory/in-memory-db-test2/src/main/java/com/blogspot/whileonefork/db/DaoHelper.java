package com.blogspot.whileonefork.db;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public class DaoHelper {
    private static final int MAX_RESULT_SIZE = 128;

    @PersistenceContext
    private EntityManager entityManager;
    
    @Transactional(isolation = Isolation.READ_UNCOMMITTED, propagation = Propagation.REQUIRED)
    public <T> List<T> list(Class<T> type, Predicate[] restrictions, int maxResults) {
        if (type == null) {
            throw new IllegalArgumentException("Type must be provided.");
        }
        if (maxResults < 1) {
            throw new IllegalArgumentException("The number of results can't be required to be < 1");
        }
        if ( restrictions == null) {
            restrictions = new Predicate[0];
        }
        
        CriteriaBuilder builder = entityManager.getEntityManagerFactory().getCriteriaBuilder();
        CriteriaQuery<T> criteria = builder.createQuery(type);
        Root<T> entityRoot = criteria.from(type);
        criteria.select(entityRoot);
        
        TypedQuery<T> query = entityManager.createQuery(criteria.where(restrictions));
        query.setMaxResults(Math.min(maxResults, MAX_RESULT_SIZE));
        return query.getResultList();
    }


    @Transactional(isolation = Isolation.READ_UNCOMMITTED, propagation = Propagation.REQUIRED)
    public <T> T findById(Class<T> type, long id) {
        T result = entityManager.find(type, id);
        return result;
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public <T> T write(T instance) {
        T savedInstance = entityManager.merge(instance);
        return savedInstance;
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public <T> void delete(T instance) {
        entityManager.remove(instance);
    }
}
