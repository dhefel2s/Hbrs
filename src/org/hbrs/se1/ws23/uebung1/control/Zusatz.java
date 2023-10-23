package org.hbrs.se1.ws23.uebung1.control;

public class Zusatz{
    /**
     * factory Method pattern kapitel 6 GoF Pattern, gibt noch dependency Injection pattern
     * Problem: Inkosistente Objekt-Erzeugung und Paratmetrisierung
     * Lösung: Service-Klasse für die zentrale und konsistente Erzeugung
     */

    public static Translator create(){
        Translator translator = new GermanTranslator();
        return translator;
    }
}
