package com.murach.dao;

import com.murach.util.EntityManagerUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceException;

import java.util.List;

public abstract class BaseDAO<T> {
    private final Class<T> entityClass;

    // Constructor accepts the entity class type for generic operations
    public BaseDAO(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    public void create(T entity) {
        EntityManager em = EntityManagerUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(entity);
            em.getTransaction().commit();
        } catch (PersistenceException e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace(); // Replace with logging in production
        } finally {
            em.close();
        }
    }

    public T find(Object id) {
        EntityManager em = EntityManagerUtil.getEntityManager();
        try {
            return em.find(entityClass, id);
        } catch (PersistenceException e) {
            e.printStackTrace(); // Replace with logging in production
            return null;
        } finally {
            em.close();
        }
    }

    public List<T> findAll() {
        EntityManager em = EntityManagerUtil.getEntityManager();
        try {
            return em.createQuery("FROM " + entityClass.getName(), entityClass).getResultList();
        } catch (PersistenceException e) {
            e.printStackTrace(); // Replace with logging in production
            return null;
        } finally {
            em.close();
        }
    }

    public void update(T entity) {
        EntityManager em = EntityManagerUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(entity);
            em.getTransaction().commit();
        } catch (PersistenceException e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace(); // Replace with logging in production
        } finally {
            em.close();
        }
    }

    public void delete(Object id) {
        EntityManager em = EntityManagerUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            T entity = em.find(entityClass, id);
            if (entity != null) {
                em.remove(entity);
            }
            em.getTransaction().commit();
        } catch (PersistenceException e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace(); // Replace with logging in production
        } finally {
            em.close();
        }
    }
}
