package com.caci.tools.vat.model;

/**
 * @author mohamed.chouchene
 *
 */
import java.util.ArrayList;

public class Panier {

	
	//Default Constructor
		public  Panier() {}
		
		//Private properties
		private ArrayList<Ligne> lignes  ;
		private float taxes;
		private float total;

		//Getters and Setters of the POJO	
 
		public float getTaxes() {
			return taxes;
		}
		public ArrayList<Ligne> getLignes() {
			return lignes;
		}
		public void setLignes(ArrayList<Ligne> lignes) {
			this.lignes = lignes;
		}
		public void setTaxes(float taxes) {
			this.taxes = taxes;
		}
		public float getTotal() {
			return total;
		}
		public void setTotal(float total) {
			this.total = total;
		}
		
		
}
