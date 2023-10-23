package org.hbrs.se1.ws23.uebung2;

public class ContainerException extends Exception{

    public ContainerException(int id) {
        super("Das Member-Objekt mit der ID " + id + " ist bereits vorhanden!");
    }
}
