package org.hbrs.se1.ws23.uebung4.Control;

import org.hbrs.se1.ws23.uebung2.ContainerException;
import org.hbrs.se1.ws23.uebung4.Model.Container;
import org.hbrs.se1.ws23.uebung4.Model.Persistence.PersistenceException;
import org.hbrs.se1.ws23.uebung4.Model.Persistence.PersistenceStrategyStream;

public class Main {
    public static void main(String[] args) throws PersistenceException, ContainerException {
        try {
            Container container = Container.getInstance();
            PersistenceStrategyStream pers = new PersistenceStrategyStream();
            container.setPersistenceStrategy(pers);
            pers.setLOCATION("/Users/daniel/DOKUMENT/");
            container.startProgramm();
        } catch (PersistenceException e){
            e.printStackTrace();
        } catch (ContainerException e){
            e.printStackTrace();
        }
    }
}
