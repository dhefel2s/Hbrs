package org.hbrs.se1.ws23.uebung1.control;


import java.util.HashMap;

public class GermanTranslator implements Translator {

	public String date = "Okt/2023"; // Default-Wert

	/**
	 * Methode zur Übersetzung einer Zahl in eine String-Repraesentation
	 */
	public String translateNumber( int number ){
		HashMap<Integer, String> zahl = new HashMap<Integer, String>();
		zahl.put(1, "eins");
		zahl.put(2, "zwei");
		zahl.put(3, "drei");
		zahl.put(4, "vier");
		zahl.put(5, "fünf");
		zahl.put(6, "sechs");
		zahl.put(7, "sieben");
		zahl.put(8, "acht");
		zahl.put(9, "neun");
		zahl.put(10, "zehn");
		return zahl.getOrDefault(number,"Übersetzung der Zahl " + number + " nicht möglich 1.0");
	}

	/**
	 * Objektmethode der Klasse GermanTranslator zur Ausgabe einer Info.
	 */
	public void printInfo(){
		System.out.println( "GermanTranslator v1.9, erzeugt am " + this.date );
	}

	/**
	 * Setzen des Datums, wann der Uebersetzer erzeugt wurde (Format: Monat/Jahr (Beispiel: "Okt/2022"))
	 * Das Datum sollte system-intern durch eine Control-Klasse gesetzt werden und nicht von externen View-Klassen
	 */
	public void setDate( String date ) {
		this.date = date;
	}

}
