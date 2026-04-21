package util;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class HibernateInitializer implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println(">>> Initialisation Hibernate au démarrage...");
        try {
            HibernateUtil.getSessionFactory();
            System.out.println(">>>Tables créées !");
        } catch (Exception e) {
            System.out.println(">>> Hibernate ERREUR : " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        HibernateUtil.shutdown();
        System.out.println(">>> Hibernate arrêté.");
    }
}