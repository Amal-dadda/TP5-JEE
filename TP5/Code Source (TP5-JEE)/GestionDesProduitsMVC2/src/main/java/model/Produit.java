package model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "produits")
public class Produit {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id ;
	
	@Column(nullable = false)
	private String nom ;
	
	@Column
	private String description;
	
	@Column(nullable = false)
	private double prix;
	
	
	
	
	
	public Produit() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public Produit(String nom, String description, double prix) {
		super();
		this.nom = nom;
		this.description = description;
		this.prix = prix;
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getPrix() {
		return prix;
	}
	public void setPrix(double prix) {
		this.prix = prix;
	}
	
	
}
