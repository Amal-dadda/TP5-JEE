package dao;

import java.util.List;

import org.hibernate.Session;

import jakarta.transaction.Transaction;
import model.Produit;
import util.HibernateUtil;

public class ProduitImp implements ProduitDAO {

	@Override
	public void add(Produit p) {
		// TODO Auto-generated method stub
		org.hibernate.Transaction  tr = null ;
		Session session = HibernateUtil.getSessionFactory().openSession() ;
		
		tr = session.beginTransaction() ; 
		session.persist(p);
		
		tr.commit();
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		 org.hibernate.Transaction  tr = null ;
		 
		 Session session = HibernateUtil.getSessionFactory().openSession() ;
		 
	     tr = session.beginTransaction();
	     Produit p = session.get(Produit.class, id);
	     if (p != null) session.remove(p);
	     tr.commit();
		
	}

	@Override
	public Produit getById(int id) {
		// TODO Auto-generated method stub
		 try (Session session = HibernateUtil.getSessionFactory().openSession()) {
	            return session.get(Produit.class, id);
	        }
	}

	@Override
	public List<Produit> getAll() {
		// TODO Auto-generated method stub
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Produit", Produit.class).list();
        }
	}

	@Override
	public void update(Produit p) {
		// TODO Auto-generated method stub
		org.hibernate.Transaction  tr = null ;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tr = session.beginTransaction();
            session.merge(p);
            tr.commit();
        } catch (Exception e) {
            if (tr != null) tr.rollback();
            throw e;
        }
    }
		


}
