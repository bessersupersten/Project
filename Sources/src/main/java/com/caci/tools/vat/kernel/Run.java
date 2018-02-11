package com.caci.tools.vat.kernel;

import java.io.File;

public class Run {
	
	private static final String OUT = "OUT_";
	private static final String SEP = "\\";
	

	public static void main(String[] args) {
		
		Parser parser = Parser.getInstance();
		Calculateur calculateur = Calculateur.getInstance();

		File dir = new File(args[0]);
		File[] directoryListing = dir.listFiles();

		System.out.println("Démarrage du programme avec les paramètres suivants:");
		System.out.println("Dossier source: " +args[0]);
		System.out.println("Dossier Destination: "+ args[1]);
		if (directoryListing != null) {
			for (File child : directoryListing) {

				calculateur.imprimerFacture(
						parser.parseInput(args[0] + SEP + child.getName()),
						args[1] + SEP + OUT + child.getName());

			}

		}
		
		System.out.println("Fin du programme. Les factures ont été générées sous:"+ args[1]);

	}

}
