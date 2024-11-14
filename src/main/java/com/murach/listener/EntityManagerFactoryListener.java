package com.murach.listener;

import com.murach.util.EntityManagerUtil;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class EntityManagerFactoryListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        // Dùng để khởi tạo database khi mới chạy chương trình
        try {
            EntityManagerUtil.getEntityManagerFactory();

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // Close EntityManagerFactory on application shutdown
        EntityManagerUtil.closeEntityManagerFactory();
    }
}
