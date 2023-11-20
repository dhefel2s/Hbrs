package org.hbrs.se1.ws23.uebung4.Model;

import java.util.Comparator;

public class Userstory implements java.io.Serializable,Comparator<Userstory> {

    private int id;
    private String desc;
    private String krit;
    private int mehrwert;
    private int strafe;
    private int Aufwand;
    private int risiko;
    private double prio;

    public Userstory(){
        this.id = 0;
        this.desc = "";
        this.krit = "";

        this.mehrwert = 0;
        this.strafe = 0;
        this.Aufwand = 0;
        this.risiko = 0;
        this.prio = getprio();
    }



    public void setId(int id) throws IllegalArgumentException{
        if(id<0){
            throw new IllegalArgumentException();
        }
        this.id = id;
    }
    public int getid(){
        return this.id;
    }
    public void setdesc(String desc) throws IllegalArgumentException{
        this.desc = desc;

    }
    public String getdesc(){
        return this.desc;
    }
    public void setkrit(String krit) throws IllegalArgumentException{
        this.krit = krit;
    }
    public String getkrit(){
        return this.krit;
    }

    public void setMehrwert(int mehrwert) throws IllegalArgumentException {
        if (strafe >= 0) {
            this.mehrwert = mehrwert;
        } else {
            throw new IllegalArgumentException("Wert darf nicht negativ sein");
        }
    }


    public int getMehrwert(){
        return this.mehrwert;
    }

    public void setStrafe(int strafe) throws IllegalArgumentException{
        if(strafe >= 0) {
            this.strafe = strafe;
        } else {
            throw new IllegalArgumentException("Wert darf nicht negativ sein");
        }

    }
    public int getStrafe(){
        return this.strafe;
    }
    public void setAufwand(int Aufwand) throws IllegalArgumentException{
        if(Aufwand >=0) {
            this.Aufwand = Aufwand;
        } else {
            throw new IllegalArgumentException("Wert darf nicht negativ sein");
        }
    }
    public int getAufwand(){
        return this.Aufwand;
    }

    public void setrisiko(int risiko) throws IllegalArgumentException{
        if(risiko >= 0) {
            this.risiko = risiko;
        } else {
            throw new IllegalArgumentException("Wert darf nicht negativ sein");
        }

    }
    public int getrisiko(){
        return this.risiko;
    }

    public void setPrio(){
        this.prio = (double)(mehrwert+strafe)/(Aufwand+risiko);
    }
    public double getprio(){
        return this.prio;
    }

    public String toString(){
        return "ID " +this.id + " beschreibung " +this.desc + " Kriterium " +this.krit + " Aufwand " +this.Aufwand + " Risiko " +this.risiko + " mehrwert " + this.mehrwert + " strafe " + this.strafe + " Prio " + this.prio;
    }


    @Override
    public int compare(Userstory o, Userstory b) {
        return Double.compare(b.getprio(), o.getprio());
    }
}
