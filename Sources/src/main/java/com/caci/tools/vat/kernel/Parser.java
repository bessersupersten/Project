package com.caci.tools.vat.kernel;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import com.caci.tools.vat.model.Categorie;
import com.caci.tools.vat.model.Ligne;
import com.caci.tools.vat.model.Panier;
import com.caci.tools.vat.model.Produit;

public class Parser {
	
	private final static String DELIMITER = ";";
	private final static String IMPORTE = "oui";
	private final static String EMPTY_STRING = "";

	// Constructeur privé
	private Parser() {
	}

	// Instance unique du Parser
	private static Parser INSTANCE = new Parser();

	// Point d'accès pour l'instance unique du singleton 'Parser'
	public static Parser getInstance() {
		return INSTANCE;
	}

	/**
	 * @param fileName
	 * @return Panier
	 */
	public Panier parseInput(String fileName) {

		Panier panier = new Panier();
		ArrayList<Ligne> lignes = new ArrayList<Ligne>();
		String line = EMPTY_STRING;

		try {

			BufferedReader bufferreader = new BufferedReader(new FileReader(
					fileName));
			line = bufferreader.readLine();

			while (line != null) {
				
				//construire une ligne du panier
				lignes.add(construireLigne(line));
				
				line = bufferreader.readLine();
			}
			bufferreader.close();

		} catch (FileNotFoundException ex) {
			
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		
		//Mettre les lignes générées dans le panier
		panier.setLignes(lignes);
		
		return panier;
	}


	
	/**
	 * @param line
	 * @return Ligne
	 */
	private Ligne construireLigne(String line) {
		String[] elements = line.split(DELIMITER);
		
		//Extraire les donnée du produit
		Produit produit = new Produit();
		produit.setDescription(elements[1]);
		produit.setPrix(Double.valueOf(elements[2]));
		produit.setImporte(elements[3].equals(IMPORTE) ? true : false);
		produit.setCategorie(Categorie.valueOf(elements[4]));
		
		//Créer la ligne correspondante
		Ligne ligne = new Ligne();
		ligne.setProduit(produit);
		ligne.setQuantite(Integer.valueOf(elements[0]));
		
		return ligne;
	}

}
