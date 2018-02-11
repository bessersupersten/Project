 package com.caci.tools.vat.model;

/**
 * @author mohamed.chouchene
 *
 */
public class Ligne {

	//Default Constructor
	public  Ligne() {}
	
	//Private properties
	private Produit produit;
	private int quantite;
	
	
	//Getters and Setters of the POJO	
	public Produit getProduit() {
		return produit;
	}
	public void setProduit(Produit produit) {
		this.produit = produit;
	}
	public int getQuantite() {
		return quantite;
	}
	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}
 
	
}
