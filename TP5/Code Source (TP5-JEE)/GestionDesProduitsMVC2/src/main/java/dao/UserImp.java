package dao;

import org.hibernate.Session;

import org.hibernate.Transaction;
import model.User;
import util.HibernateUtil;

public class UserImp implements UserDAO {

	@Override
	public void registerUser(User user) {
	    Transaction tr = null;
	    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
	       
	        tr = session.beginTransaction();
	        session.persist(user);
	        tr.commit();
	       
	    } catch (Exception e) {
	        if (tr != null) tr.rollback();
	   
	        e.printStackTrace();
	    }
	}
	
	

	@Override
	public User loginUser(String username, String password) {
		// TODO Auto-generated method stub
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(
                "FROM User WHERE username = :u AND password = :p", User.class)
                .setParameter("u", username)
                .setParameter("p", password)
                .uniqueResult();
        }
    
       
	}

}
