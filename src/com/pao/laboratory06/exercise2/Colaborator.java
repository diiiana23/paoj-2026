package com.pao.laboratory06.exercise2;

import java.util.Scanner;

public abstract class Colaborator implements IOperatiiCitireScriere {
    protected String nume;
    protected String prenume;
    protected double venitBrutLunar;

    public abstract void citeste(Scanner in);
    public abstract void afiseaza();
    public abstract String tipContract();

    public abstract double calculeazaVenitNetAnual();
    public abstract TipColaborator getTip();
}