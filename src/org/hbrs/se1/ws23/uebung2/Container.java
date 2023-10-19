package org.hbrs.se1.ws23.uebung2;

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

    ArrayList<Member> container;

    public Container() {
        container = new ArrayList<Member>();
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
    public void dump(){
        for(Member members: container){
            System.out.println(members.toString());
        }
    }
    public int size(){
        return container.size();
        }
    }

