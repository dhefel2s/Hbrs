package org.hbrs.se1.ws23.uebung4.Model;

import org.hbrs.se1.ws23.uebung2.ContainerException;
import org.hbrs.se1.ws23.uebung4.Control.Input;
import org.hbrs.se1.ws23.uebung4.Model.Persistence.PersistenceException;
import org.hbrs.se1.ws23.uebung4.Model.Persistence.PersistenceStrategy;
import org.hbrs.se1.ws23.uebung4.Model.Persistence.PersistenceStrategyStream;
import org.hbrs.se1.ws23.uebung4.View.UserStoryview;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Container {



    private PersistenceStrategy<Userstory> persistenceStrategy;
    private ArrayList<Userstory> Liste;
    private static Container instance = null;

    private boolean connectionOpen = false;




    private Container() {
        Liste = new ArrayList<Userstory>();
    }

    public void startProgramm() throws PersistenceException, ContainerException {
        Scanner scanner = new Scanner(System.in);
        String strInput;
        System.out.println("Herzlich Willkommen beim UserstoryManagement");
        System.out.println("Drücken sie help für ihre Befehle");
        while (true) {

            System.out.print("> ");

            strInput = scanner.nextLine();


            String[] strings = strInput.split(" ");


            if (strings[0].equals("help")) {
                System.out.println("Folgende Befehle stehen zur Verfuegung: Enter, help, dump, load, search, store, exit");
            }

            if (strings[0].equals("dump")) {
                UserStoryview user = new UserStoryview();
                user.dump(this.Liste);
            }

            if (strings[0].equals("enter")) {
                System.out.println("Erstellen sie ihre UserStory");
                addUserstory(Input.enter());
            }

            if (strings[0].equals("store")) {
                this.store();
            }
            if (strings[0].equals("exit")) {
                System.out.println("Programm wird beendet");
                break;
            }
            if (strings[0].equals("load")) {
                this.load();
            }
            if (strings[0].equals("search")) {
                System.out.println("Nach welchem Wort suchen wir?");
                UserStoryview user = new UserStoryview();
                user.search(this.Liste);
            }
        }
    }
    public static synchronized Container getInstance() {
        if(instance == null){
            return new Container(); // synchronized, thread safe, höhere laufzeit, static klassenmethode, zuvor keine instanzobjekt, objektvariable kann nicht in static methode
        }
        return instance;
    }
    public void addUserstory(Userstory userstory) throws ContainerException {
        for (Userstory userstory1 : Liste) {
            int i = userstory1.getid();
            if (i == userstory.getid()) {
                throw new ContainerException(userstory.getid());
            }
        }
        Liste.add(userstory);
    }
    public void store() throws PersistenceException {
        if (this.persistenceStrategy == null){
            throw new PersistenceException(PersistenceException.
                    ExceptionType.NoStrategyIsSet,"Strategy not initialized");
        }
        if(connectionOpen == false) {
            this.openConnection();
            connectionOpen = true;
        }
        this.persistenceStrategy.save(Liste);
        closeConnection();
    }

    public void load() throws PersistenceException {
        if (this.persistenceStrategy == null)
            throw new PersistenceException(PersistenceException.
                    ExceptionType.NoStrategyIsSet,"Strategy not initialized");

        if (connectionOpen == false) {
            this.openConnection();
            connectionOpen = true;
        }
        List<Userstory> liste = this.persistenceStrategy.load();
        this.Liste = (ArrayList<Userstory>) liste;

    }
    private void openConnection() throws PersistenceException {
        try {
            this.persistenceStrategy.openConnection();
            connectionOpen = true;
        } catch( UnsupportedOperationException e ) {
            throw new PersistenceException(PersistenceException.
                    ExceptionType.NoStrategyIsSet,"Not implemented!");
        }
    }

    private void closeConnection() throws PersistenceException {
        try {
            this.persistenceStrategy.closeConnection();
            connectionOpen = false;
        } catch( UnsupportedOperationException e ) {
            throw new PersistenceException(PersistenceException.
                    ExceptionType.NoStrategyIsSet,"Not implemented!" );
        }
    }


    public ArrayList<Userstory> getCurrentList() {
        return Liste;
    }
    public void setPersistenceStrategy(PersistenceStrategy persistenceStrategy) {
        if (connectionOpen == true) {
            try {
                this.closeConnection();
            } catch (PersistenceException e) {
                e.printStackTrace();
            }
        }
        this.persistenceStrategy = persistenceStrategy;
    }
    public PersistenceStrategy<Userstory> getPersistenceStrategy() {
        return persistenceStrategy;
    }

    public int size(){
        return Liste.size();
    }
}

