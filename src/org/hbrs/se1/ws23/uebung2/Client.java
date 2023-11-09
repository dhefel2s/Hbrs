package org.hbrs.se1.ws23.uebung2;

import java.util.ArrayList;

public class Client {
    // cr1: Singleton pattern und strategy pattern
    // main = assembler
        public static void main(String[] args) {

            try {
                Container container = Container.getInstance();
                Memberview memberView = new Memberview();

                // Member-Objekte erstellen und zum Container hinzufügen
                ConcreteMember member1 = new ConcreteMember(1);
                ConcreteMember member2 = new ConcreteMember(2);
                container.addMember(member1);
                container.addMember(member2);

                ArrayList<Member> container1 = container.getCurrentList();

                // Member-Objekte in MemberView ausgeben
                memberView.dump(container1);
            } catch (ContainerException e){
            }
        }
    }

