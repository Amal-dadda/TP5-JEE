package service;

import java.util.List;

import dao.ProduitDAO;
import dao.ProduitImp;
import model.Produit;

public class ProduitServiceImp implements ProduitService {
	

    private static ProduitServiceImp instance;
    private ProduitDAO dao = new ProduitImp();

    private ProduitServiceImp() {}

    public static ProduitServiceImp getInstance() {
        if (instance == null) instance = new ProduitServiceImp();
        return instance;
    }

    @Override 
    public void add(Produit p){ 
    	dao.add(p); 
    }
    
    
    @Override 
    public void delete(int id){ 
    	dao.delete(id);
    }
    
    @Override 
    public Produit getById(int id){ 
    	return dao.getById(id);
    }
    
    @Override 
    public List<Produit> getAll(){ 
    	return dao.getAll();
    }
    @Override 
    public void update(Produit p) { dao.update(p); }


}
