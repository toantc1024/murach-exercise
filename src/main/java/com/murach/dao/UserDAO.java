package com.murach.dao;

import com.murach.dto.User;
import com.murach.util.EntityManagerUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;

public class UserDAO extends BaseDAO<User> {
    public UserDAO() {
        super(User.class);
    }

    public User findUserByEmail(String email) {
        EntityManager em = EntityManagerUtil.getEntityManager();
        try {
            TypedQuery<User> query = em.createQuery(
                    "SELECT u FROM User u WHERE u.email = :email", User.class
            );
            query.setParameter("email", email);
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null; // No user found with the given email
        } finally {
            em.close();
        }
    }
}
