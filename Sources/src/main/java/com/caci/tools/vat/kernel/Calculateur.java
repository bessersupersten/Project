package com.caci.tools.vat.kernel;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import com.caci.tools.vat.model.Categorie;
import com.caci.tools.vat.model.Ligne;
import com.caci.tools.vat.model.Panier;
import com.caci.tools.vat.model.Produit;

/**
 * @author mohamed.chouchene
 *
 */
public class Calculateur {

	private final static String RETURN = "\r\n";
	private final static String FACT = "Facture du:  ";
	private final static String SEPERATOR = "____________________________________________________";
	private final static String STAR = "* ";
	private final static String SPACE = " ";
	private final static String A = " à ";
	private final static String EUROSPACE = "€  : ";
	private final static String EUROTTC = "€ TTC";
	private final static String TOTALTAXES = "Montant des taxes : ";
	private final static String EURO = "€";
	private final static String TOTAL = "Total : ";
	private final static String IMP = "Importé ";
	private final static String EMPTY = "";

	// Constructeur privé
	private Calculateur() {
	}

	// Instance unique du calculateur
	private static Calculateur INSTANCE = new Calculateur();

	// Point d'accès pour l'instance unique du singleton 'calculateur'
	public static Calculateur getInstance() {
		return INSTANCE;
	}
	
	

 
	/**
	 * Cette méthode permet d'imprimer une facture d'un panier donné.
	 * @param panier
	 * @param outFile
	 */
	public void imprimerFacture(Panier panier, String outFile) {
		double totalHt = 0;
		double totalttc = 0;

		StringBuilder builder = new StringBuilder();

		// Itérer sur les lignes du panier
		ArrayList<Ligne> lignes = panier.getLignes();
		Iterator<Ligne> itr = lignes.iterator();

		builder.append(FACT + new Date().toString()).append(RETURN);
		builder.append(SEPERATOR).append(RETURN);
		
		// Itérer sur les lignes de la commande
		while (itr.hasNext()) {
			Ligne ligne = itr.next();
			Produit produit = ligne.getProduit();

			//Calculer le montant TTC et TVA par ligne
			double ttcLigne = calculerTTCLigne(ligne);
			
			// Imprimer les données d'une ligne
			builder.append(
					STAR + ligne.getQuantite() + SPACE
							+ produit.getDescription() + SPACE
							+ (produit.isImporte() ? IMP : EMPTY) + A
							+ format(produit.getPrix()) + EUROSPACE
							+ format(ttcLigne) + EUROTTC).append(
					RETURN);
			totalttc = totalttc + ttcLigne;
			totalHt = totalHt + (produit.getPrix() * ligne.getQuantite());
		}

		builder.append(SEPERATOR).append(RETURN);
		builder.append(TOTALTAXES + format(totalttc - totalHt) + EURO).append(
				RETURN);
		builder.append(TOTAL + format(totalttc) + EURO).append(RETURN);
		builder.append(SEPERATOR).append(RETURN);

		iprimerOutput(builder, outFile);
	}

	/**
	 * 
	 * Cette méthode permet d'imprimer le contenu de la facture générée dans un
	 * fichier
	 * 
	 * @param builder
	 * @param outFile
	 */
	private void iprimerOutput(StringBuilder builder, String outFile) {
		try {
			File file = new File(outFile);
			FileWriter fileWriter = new FileWriter(file);
			fileWriter.write(builder.toString());
			fileWriter.flush();
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Cette méthode permet de calculer le total TTC pour une ligne
	 * */
	private double calculerTTCLigne(Ligne ligne) {
		double tvaLigne =  ligne.getQuantite()
				* calculerTvaProduit(ligne.getProduit());
		double htLigne = ligne.getQuantite()
				* ligne.getProduit().getPrix();
		double ttcLigne = htLigne + (arrondir(tvaLigne * 100) / 100);
		return  (arrondir(ttcLigne * 100) / 100);
	}
	
	
	/**
	 * Cette méthode permet de calculer la taxe TVA à appliquer selon le type du
	 * produit et son prix.
	 * @param produit
	 * @return double
	 */
	private double calculerTvaProduit(Produit produit) {
		// Calculer le taux à appliuqer
		int taux = determinerTaux(produit);

		// Calculer le montant de la taxe
		double tva = (produit.getPrix() * taux) / 100;

		return tva;
	}

	
 
	/**
	 * Cette méthode permet d'arrondir le montant de la taxe aux 5 centimes
	 * supérieurs
	 * 
	 * @param tva
	 * @returndouble
	 */
	private double arrondir(double tva) {
		return 5 * (Math.ceil(Math.abs(tva / 5)));
	}
	
	
  
	/**
	 * Cette méthode permet determiner le taux TVA à appliquer pour un produit
	 * donnée.
	 * @param produit
	 * @return
	 */
	private int determinerTaux(Produit produit) {

		int taxe = 0;

		Categorie categorie = produit.getCategorie();
		switch (categorie) {
		case PRIMARY:
			taxe = 0;
			break;
		case BOOKS:
			taxe = 10;
			break;
		case OTHER:
			taxe = 20;
			break;
		}

		// Vérifier si le produit est importé
		if (produit.isImporte()) {
			taxe = taxe + 5;
		}

		return taxe;
	}

 
	/**
	 * Cette méthode permet de formater un montant à 2 décimales
	 * @param double
	 * @return String
	 */
	private String format(double d) {
		NumberFormat formatter = new DecimalFormat("#0.00");
		return (formatter.format(d));
	}
}
