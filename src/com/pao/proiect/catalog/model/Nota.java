package com.pao.proiect.catalog.model;

import java.util.Objects;

public class Nota {
    private int valoare;
    private Materie materie;
    public Nota(int valoare, Materie materie){
        this.valoare = valoare;
        this.materie = materie;
    }

    public int getValoare(){
        return valoare;
    }
    public void setValoare(int valoare){
        this.valoare = valoare;
    }
    public Materie getMaterie(){
        return materie;
    }
    public void setMaterie(Materie materie){
        this.materie = materie;
    }

    @Override
    public String toString(){
        return "Nota la " + materie + ": " + valoare;
    }
    @Override
    public boolean equals(Object obj){
        if (this == obj)
            return true;
        if (!(obj instanceof Nota))
            return false;
        Nota n = (Nota) obj;
        return valoare == n.valoare && materie.equals(n.materie);
    }
    @Override
    public int hashCode(){
        return Objects.hash(valoare, materie);
    }
}