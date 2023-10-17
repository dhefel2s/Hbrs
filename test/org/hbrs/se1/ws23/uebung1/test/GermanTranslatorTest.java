package org.hbrs.se1.ws23.uebung1.test;

import org.hbrs.se1.ws23.uebung1.control.GermanTranslator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GermanTranslatorTest {

    @Test
    void aPositiveTest() {
        GermanTranslator translator = new GermanTranslator();
        String value = translator.translateNumber(5);       //Äquivalenzklasse: 1 < x < 11
        assertEquals(value, "fünf");
    }
    @Test
    void aNegativeTest() {
        GermanTranslator translator = new GermanTranslator();
        String value = translator.translateNumber(-2);
        assertEquals(value, "Übersetzung der Zahl -2 nicht möglich 1.0"); //Äquivalenzklasse: x < 1

        GermanTranslator translator2 = new GermanTranslator();
        String valuee = translator.translateNumber(33);
        assertEquals(valuee, "Übersetzung der Zahl 33 nicht möglich 1.0");  //Äquivalenzklasse: x > 10

    }
}