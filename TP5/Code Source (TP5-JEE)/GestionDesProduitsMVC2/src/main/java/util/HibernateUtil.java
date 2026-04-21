package util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;



public class HibernateUtil {
	
	private static Configuration cfg = new Configuration().configure("hibernate.cfg.xml");
	private static SessionFactory sessionFactory = cfg.buildSessionFactory() ;
	
	
	
	
	public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutdown() {
        getSessionFactory().close();
    }
	
	
	
	}
