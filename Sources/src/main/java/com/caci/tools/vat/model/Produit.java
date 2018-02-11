package com.caci.tools.vat.model;

/**
 * @author mohamed.chouchene
 *
 */
public class Produit {

	//Default Constructor
	public  Produit() {}
	
	//Private properties
	private String description;
	private double prix;
	private boolean importe;
	private Categorie categorie;
	
	
	//Getters and Setters of the POJO	
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
	public boolean isImporte() {
		return importe;
	}
	public void setImporte(boolean importe) {
		this.importe = importe;
	}
	public Categorie getCategorie() {
		return categorie;
	}
	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}
	
	
}
