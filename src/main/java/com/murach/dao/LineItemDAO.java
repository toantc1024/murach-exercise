package com.murach.dao;

import com.murach.dto.LineItem;
import com.murach.util.EntityManagerUtil;
import jakarta.persistence.EntityManager;

public class LineItemDAO extends BaseDAO<LineItem> {
    public LineItemDAO() {
        super(LineItem.class);
    }

    public void updateQuantity(int lineItemId, int quantity) {
        EntityManager em = EntityManagerUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            LineItem lineItem = em.createQuery("SELECT l FROM LineItem l WHERE l.id = :lineItemId ", LineItem.class)
                    .setParameter("lineItemId", lineItemId)
                    .getSingleResult();
            lineItem.setQuantity(quantity);
            em.merge(lineItem);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
}
