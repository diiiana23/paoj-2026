package com.pao.laboratory06.exercise3;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Inginer[] ingineri = {
                new Inginer("Popescu", "Ana", "071", 8000),
                new Inginer("Ionescu", "Vlad", "072", 7000),
                new Inginer("Georgescu", "Maria", "073", 9000)
        };
        Arrays.sort(ingineri);
        System.out.println("Sortare după nume:");
        for (Inginer i : ingineri) System.out.println(i);

        Arrays.sort(ingineri, new ComparatorInginerSalariu());
        System.out.println("\nSortare după salariu:");
        for (Inginer i : ingineri) System.out.println(i);

        PlataOnline p = ingineri[0];
        p.autentificare("user", "pass");
        System.out.println("Sold: " + p.consultareSold());

        PersoanaJuridica pj = new PersoanaJuridica("Firma", "SRL", "074");
        pj.trimiteSMS("Salut");
        pj.trimiteSMS("");
        System.out.println("SMS-uri: " + pj.getSmsTrimise());

        System.out.println("\nTVA: " + ConstanteFinanciare.TVA.getValoare());

        try {
            p.autentificare("", "");
        } catch (Exception e) {
            System.out.println("Eroare: " + e.getMessage());
        }
    }
}