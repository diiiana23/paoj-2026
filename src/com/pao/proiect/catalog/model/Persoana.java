package com.pao.proiect.catalog.model;

public abstract class Persoana {
    protected int id;
    protected String nume;

    public Persoana(int id, String nume){
        this.id = id;
        this.nume = nume;
    }

    public int getId(){
        return id;
    }
    public String getNume(){
        return nume;
    }
    public abstract String getRol();
}