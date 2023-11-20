package uebung4test;

import org.hbrs.se1.ws23.uebung2.ContainerException;
import org.hbrs.se1.ws23.uebung4.Model.Container;
import org.hbrs.se1.ws23.uebung4.Model.Userstory;
import org.hbrs.se1.ws23.uebung4.Model.Persistence.*;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class UserstoryTest {

    private Container container;

    @BeforeEach
    void setUp() {
        container = Container.getInstance();
    }



    @Test
    void testAddUserstory() throws ContainerException {
        Userstory userstory = new Userstory();
        userstory.setId(1);
        userstory.setdesc("Test Userstory");
        userstory.setkrit("Test Akzeptanzkriterium");
        userstory.setMehrwert(5);
        userstory.setAufwand(3);
        userstory.setStrafe(1);
        userstory.setrisiko(2);

        assertDoesNotThrow(() -> container.addUserstory(userstory));

        assertEquals(1, container.getCurrentList().size());
        assertEquals(userstory, container.getCurrentList().get(0));
    }

    @Test
    void testAddDuplicateUserstory() {
        assertThrows(ContainerException.class, () -> {
            Userstory userstory1 = new Userstory();
            userstory1.setId(1);
            userstory1.setdesc("Test Userstory 1");

            Userstory userstory2 = new Userstory();
            userstory2.setId(1);
            userstory2.setdesc("Test Userstory 2");

            container.addUserstory(userstory1);
            container.addUserstory(userstory2);
        });
    }

    @Test
    void testStoreAndLoad() throws PersistenceException, ContainerException {
        PersistenceStrategy<Userstory> persistenceStrategy = new PersistenceStrategyStream();
        container.setPersistenceStrategy(persistenceStrategy);

        Userstory userstory = new Userstory();
        userstory.setId(1);
        userstory.setdesc("Test Userstory");
        userstory.setkrit("Test Akzeptanzkriterium");
        userstory.setMehrwert(5);
        userstory.setAufwand(3);
        userstory.setStrafe(1);
        userstory.setrisiko(2);
        container.addUserstory(userstory);


        assertDoesNotThrow(() -> container.store());
        assertDoesNotThrow(() -> container.load());


        assertEquals(1, container.getCurrentList().size());
        assertEquals(userstory, container.getCurrentList().get(0));
    }

}
