package org.hbrs.se1.ws23.uebung2.test;

import org.hbrs.se1.ws23.uebung2.ConcreteMember;
import org.hbrs.se1.ws23.uebung2.Container;
import org.hbrs.se1.ws23.uebung2.ContainerException;
import org.hbrs.se1.ws23.uebung2.Member;
import org.hbrs.se1.ws23.uebung3.persistence.PersistenceException;
import org.hbrs.se1.ws23.uebung3.persistence.PersistenceStrategy;
import org.hbrs.se1.ws23.uebung3.persistence.PersistenceStrategyMongoDB;
import org.hbrs.se1.ws23.uebung3.persistence.PersistenceStrategyStream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class ContainerTest {
    Container zahl;
    Member uno;
    Member zwo;

    @BeforeEach
    void innit() {
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

    @Test
    public void keineStrategy() {
        Container container = new Container();

        assertNull(container.getPersistenceStrategy());
    }

    @Test
    public void testMongoDBStrategy() {
        Container container = new Container();

        PersistenceStrategy<Member> strategy = new PersistenceStrategyMongoDB();
        container.setPersistenceStrategy(strategy);


        assertTrue(container.getPersistenceStrategy() instanceof PersistenceStrategyMongoDB);
    }

    @Test
    public void testInvalidLocation() {
        Container container = new Container();


        PersistenceStrategyStream<Member> strategy = new PersistenceStrategyStream<>();
        strategy.setLocation("invalid_directory/objects.ser");
        container.setPersistenceStrategy(strategy);

        assertThrows(PersistenceException.class, () -> container.store());
    }

    @Test
    public void testRoundTrip() {
        Container container = new Container();
        PersistenceStrategyStream<Member> strategy = new PersistenceStrategyStream<>();
        container.setPersistenceStrategy(strategy);

        try {
            // Objekt hinzufügen
            ConcreteMember member = new ConcreteMember(2);
            container.addMember(member);

            // Liste persistent abspeichern
            assertDoesNotThrow(() -> container.store());

            // Objekt aus Container löschen
            container.deleteMember(member.getID());

            // Liste wieder einladen
            assertDoesNotThrow(() -> container.load());

            // Test, ob das gelöschte Objekt wieder in der Liste ist
            assertTrue(container.getCurrentList().contains(member));
        } catch (ContainerException e) {

            e.getMessage();
        }
    }
}
