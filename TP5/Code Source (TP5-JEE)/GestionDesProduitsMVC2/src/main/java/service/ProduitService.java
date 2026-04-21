package service;

import java.util.List;

import model.Produit;

public interface ProduitService {
	void add(Produit p);
    void delete(int id);
    Produit getById(int id);
    List<Produit> getAll();
    void update(Produit p); 

}
