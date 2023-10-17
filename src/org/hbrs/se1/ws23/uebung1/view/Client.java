package org.hbrs.se1.ws23.uebung1.view;

import org.hbrs.se1.ws23.uebung1.control.Translator;
import org.hbrs.se1.ws23.uebung1.control.Zusatz;

public class Client {
	public static void main(String[] args) {
		display(11);
	}
		/*
		 * Methode zur Ausgabe einer Zahl auf der Console
		 * (auch bezeichnet als CLI, Terminal)
		 *
		 */
		static void display(int aNumber){
			 Translator translator = Zusatz.create();
			 // In dieser Methode soll die Methode translateNumber
			 // mit dem übergegebenen Wert der Variable aNumber
			 // aufgerufen werden.
			 //
			 // Strenge Implementierung gegen das Interface Translator gewuenscht!

			 System.out.println("Das Ergebnis der Berechnung: " + translator.translateNumber(aNumber));

		 }
}





