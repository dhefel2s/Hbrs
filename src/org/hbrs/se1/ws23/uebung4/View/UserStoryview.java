package org.hbrs.se1.ws23.uebung4.View;

import org.hbrs.se1.ws23.uebung4.Model.Container;
import org.hbrs.se1.ws23.uebung4.Model.Userstory;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class UserStoryview {

    public void dump(ArrayList<Userstory> list){
        list.stream()
                .sorted(new Userstory()) // Sortieren der Liste
                .map(Userstory:: toString) // Mapping der Userstory-Objekte zu ihren Zeichenkettenrepräsentationen
                .forEach(System.out::println);
            /* Collections.sort(list, new Userstory());
            for(Userstory user: list){
                System.out.println(user.toString());
        }
             */
    }

    public void search(ArrayList<Userstory> list){
        Scanner scanner = new Scanner(System.in);
        String s = scanner.next();
        for(Userstory user: list){
            if(user.getdesc().contains(s)){
                System.out.println(user.toString());
            }
        }
    }
}
