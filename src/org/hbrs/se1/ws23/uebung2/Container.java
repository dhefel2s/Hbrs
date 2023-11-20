package org.hbrs.se1.ws23.uebung2;

import org.hbrs.se1.ws23.uebung3.persistence.PersistenceException;
import org.hbrs.se1.ws23.uebung3.persistence.PersistenceStrategy;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class Container{

    public static void main(String[] args) throws ContainerException {
        Container container = getInstance();
        Container container1 = getInstance();
        Member n = new ConcreteMember(2);
        Member f = new ConcreteMember(4);
        container.addMember(n);
        container.addMember(f);
        container.addMember(new ConcreteMember(5));
        System.out.println(container.size());


    }
    private PersistenceStrategy<Member> persistenceStrategy;
    private ArrayList<Member> Liste;
    private static Container instance = null;
    private boolean connectionOpen = false;

    private Container() {
        Liste = new ArrayList<Member>();
    }
    // Thread safe??
    public static synchronized Container getInstance() {
        if(instance == null){
            return new Container(); // synchronized, thread safe, höhere laufzeit, static klassenmethode, zuvor keine instanzobjekt, objektvariable kann nicht in static methode
        }
        return instance;
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
    public PersistenceStrategy<Member> getPersistenceStrategy() {
        return persistenceStrategy;
    }

    public void store() throws PersistenceException {
        if (this.persistenceStrategy == null){
            throw new PersistenceException("Strategy not initialized");
        }
        if(connectionOpen == false) {
            this.openConnection();
            connectionOpen = true;
        }
        this.persistenceStrategy.save(Liste);
    }

    public void load() throws PersistenceException {
        if (this.persistenceStrategy == null)
            throw new PersistenceException("Strategy not initialized");

        if (connectionOpen == false) {
            this.openConnection();
            connectionOpen = true;
        }
        List<Member> liste = this.persistenceStrategy.load();
        this.Liste = (ArrayList<Member>) liste;
    }
    public void addMember(Member member) throws ContainerException {
        for (Member members : Liste) {
            int i = members.getID();
            if (i == member.getID()) {
                throw new ContainerException(member.getID());
            }
        }
        Liste.add(member);
    }

    public String deleteMember(Integer id) {
        for (Member members : Liste) {
            int i = members.getID();
            if (i == id) {
                Liste.remove(members);
                return "erfolgreiches entfernen";
            }
        }
        return "kein Objekt mit ID gefunden";
    }

    public ArrayList<Member> getCurrentList() {
        return Liste;
    }
    public int size(){
        return Liste.size();
        }

    private void openConnection() throws PersistenceException {
        try {
            this.persistenceStrategy.openConnection();
            connectionOpen = true;
        } catch( UnsupportedOperationException e ) {
            throw new PersistenceException("Not implemented!");
        }
    }

    private void closeConnection() throws PersistenceException {
        try {
            this.persistenceStrategy.closeConnection();
            connectionOpen = false;
        } catch( UnsupportedOperationException e ) {
            throw new PersistenceException("Not implemented!" );
        }
    }
    }

