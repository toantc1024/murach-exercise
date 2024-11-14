package com.murach.util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class EntityManagerUtil {

    private static volatile EntityManagerFactory entityManagerFactory;

    // Private constructor to prevent instantiation
    private EntityManagerUtil() {}

    // Thread-safe initialization of EntityManagerFactory
    public static EntityManagerFactory getEntityManagerFactory() {
        if (entityManagerFactory == null) {
            synchronized (EntityManagerUtil.class) {
                if (entityManagerFactory == null) {
                    entityManagerFactory = Persistence.createEntityManagerFactory("murach-store");
                    System.out.println("EntityManagerFactory initialized.");
                }
            }
        }
        return entityManagerFactory;
    }

    // Provides a new EntityManager for each transaction or request
    public static EntityManager getEntityManager() {
        return getEntityManagerFactory().createEntityManager();
    }

    // Close EntityManagerFactory on application shutdown
    public static void closeEntityManagerFactory() {
        if (entityManagerFactory != null && entityManagerFactory.isOpen()) {
            entityManagerFactory.close();
            System.out.println("EntityManagerFactory closed.");
        }
    }
}
