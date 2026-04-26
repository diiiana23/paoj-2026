package com.pao.proiect.catalog.model;

import java.util.Objects;

public class Materie {
    private String nume;
    public Materie(String nume){
        this.nume = nume;
    }

    public String getNume(){
        return nume;
    }
    public void setNume(String nume){
        this.nume = nume;
    }

    @Override
    public String toString(){
        return "Materia '" + nume + "'";
    }
    @Override
    public boolean equals(Object obj){
        if (this == obj)
            return true;
        if (!(obj instanceof Materie))
            return false;
        Materie m = (Materie) obj;
        return nume.equals(m.nume);
    }
    @Override
    public int hashCode(){
        return Objects.hash(nume);
    }
}