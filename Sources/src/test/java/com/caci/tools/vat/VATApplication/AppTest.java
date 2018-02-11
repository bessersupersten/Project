package com.caci.tools.vat.VATApplication;

import java.io.File;

import com.caci.tools.vat.kernel.Calculateur;
import com.caci.tools.vat.kernel.Parser;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase {

	private static final String IN_FOLDER = "src\\test\\resources\\INPUT\\";
	private static final String OUT_FOLDER = "src\\test\\resources\\OUTPUT\\";
	private static final String OUT = "OUT_";

	/**
	 * Create the test case
	 *
	 * @param testName
	 *            name of the test case
	 */
	public AppTest(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(AppTest.class);
	}

	/**
	 * Rigourous Test :-)
	 */
	public void testApp() {

		// Final Test
		Parser parser = Parser.getInstance();
		Calculateur calculateur = Calculateur.getInstance();

		File dir = new File(IN_FOLDER);
		File[] directoryListing = dir.listFiles();

		if (directoryListing != null) {
			for (File child : directoryListing) {

				calculateur.imprimerFacture(
						parser.parseInput(IN_FOLDER + child.getName()),
						OUT_FOLDER + OUT + child.getName());

			}

		}

		assertTrue(true);
	}
}
