package org.hbrs.se1.ws23.uebung2.test;

import org.hbrs.se1.ws23.uebung2.ConcreteMember;
import org.hbrs.se1.ws23.uebung2.Container;
import org.hbrs.se1.ws23.uebung2.ContainerException;
import org.hbrs.se1.ws23.uebung2.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class ContainerTest {
    Container zahl;
    Member uno;
    Member zwo;
    @BeforeEach
    void innit(){
        uno = new ConcreteMember(1);
        zwo = new ConcreteMember(2);
        zahl = new Container();
    }
    @Test
    void testphase() throws ContainerException {
        assertEquals(1, uno.getID());
        assertEquals(0, zahl.size());
        zahl.addMember(uno);
        assertEquals(1, zahl.size());
        assertThrows(ContainerException.class, () -> zahl.addMember(new ConcreteMember(1)));
        zahl.deleteMember(1);
        assertEquals(0, zahl.size());
        zahl.addMember(uno);
        assertEquals("erfolgreiches entfernen", zahl.deleteMember(1));
        assertEquals("kein Objekt mit ID gefunden", zahl.deleteMember(3));

        zahl.addMember(uno);
        assertEquals(2, zwo.getID());
        zahl.addMember(zwo);
        assertEquals(2, zahl.size());
        assertThrows(ContainerException.class, () -> zahl.addMember(new ConcreteMember(2)));
        zahl.deleteMember(1);
        assertEquals(1, zahl.size());
        assertEquals("erfolgreiches entfernen", zahl.deleteMember(2));
        assertEquals("kein Objekt mit ID gefunden", zahl.deleteMember(3));

    }
}
