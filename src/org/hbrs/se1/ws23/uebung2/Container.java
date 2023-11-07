package org.hbrs.se1.ws23.uebung2;

import org.hbrs.se1.ws23.uebung3.persistence.PersistenceException;
import org.hbrs.se1.ws23.uebung3.persistence.PersistenceStrategy;

import java.io.*;
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class Container {

    public static void main(String[] args) throws ContainerException {
        Container container = new Container();
        Member n = new ConcreteMember(2);
        Member f = new ConcreteMember(4);
        container.addMember(n);
        container.addMember(f);
        container.addMember(new ConcreteMember(5));
        container.dump();
        System.out.println(container.size());

    }
    private static Container instance = null;
    ArrayList<Member> container;
    private PersistenceStrategy<Member> persistenceStrategy;


    public Container() {
        container = new ArrayList<Member>();
    }
    public static Container getInstance() {
        if (instance == null) {
            instance = new Container();
        }
        return instance;
    }

    public void setPersistenceStrategy(PersistenceStrategy<Member> strategy) {
        this.persistenceStrategy = strategy;
    }
    public PersistenceStrategy<Member> getPersistenceStrategy() {
        return persistenceStrategy;
    }

    public void store() throws PersistenceException {
        persistenceStrategy.openConnection();
        persistenceStrategy.save(container);
        persistenceStrategy.closeConnection();
    }
    public void load() throws PersistenceException {
        persistenceStrategy.openConnection();
        container = (ArrayList<Member>) persistenceStrategy.load();
        persistenceStrategy.closeConnection();
    }
    public void addMember(Member member) throws ContainerException {
        for (Member members : container) {
            int i = members.getID();
            if (i == member.getID()) {
                throw new ContainerException(member.getID());
            }
        }
        container.add(member);
    }

    public String deleteMember(Integer id) {
        for (Member members : container) {
            int i = members.getID();
            if (i == id) {
                container.remove(members);
                return "erfolgreiches entfernen";
            }
        }
        return "kein Objekt mit ID gefunden";
    }

    public ArrayList<Member> getCurrentList() {
        return container;
    }
    public void dump(){
        for(Member members: container){
            System.out.println(members.toString());
        }
    }
    public int size(){
        return container.size();
        }
    }

