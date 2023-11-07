package org.hbrs.se1.ws23.uebung2;

import java.util.ArrayList;

public class Memberview {
    public void dump(ArrayList<Member> list){
        for(Member members: list){
            System.out.println(members.toString());
        }
    }
}
