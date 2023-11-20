package org.hbrs.se1.ws23.uebung4.Control;

import org.hbrs.se1.ws23.uebung2.ContainerException;
import org.hbrs.se1.ws23.uebung4.Model.Userstory;

import java.util.Scanner;

public class Input {
    public static Userstory enter() throws ContainerException {
        Scanner scanner = new Scanner(System.in);
        Userstory userstory = new Userstory();

        System.out.println("Geben sie eine ID ein!");
        int id = scanner.nextInt();
        userstory.setId(id);
        scanner.nextLine();
        System.out.println("Geben sie eine Beschreibung ein! ");
        String s = scanner.nextLine();
        userstory.setdesc(s);

        System.out.println("Geben sie ein Akzeptanzkriterium ein! ");
        String p = scanner.nextLine();
        userstory.setkrit(p);

        System.out.println("Geben sie den Mehrwert ein!");
        int mehr = scanner.nextInt();
        userstory.setMehrwert(mehr);

        System.out.println("Geben sie den Aufwand ein!");
        int auf = scanner.nextInt();
        userstory.setAufwand(auf);

        System.out.println("Geben sie ein Strafe ein!");
        int st = scanner.nextInt();
        userstory.setStrafe(st);

        System.out.println("Geben sie ein Risiko ein!");
        int ri = scanner.nextInt();
        userstory.setrisiko(ri);

        userstory.setPrio();
        System.out.println("Danke für ihre Eingabe");
        return userstory;
    }
}
