package com.pao.proiect.catalog.model;

public class Profesor extends Persoana {
    private String materie;
    public Profesor(int id, String nume, String materie){
        super(id, nume);
        this.materie = materie;
    }

    public String getMaterie(){
        return materie;
    }
    public void setMaterie(String materie){
        this.materie = materie;
    }

    @Override
    public String getRol(){
        return "Profesor";
    }
    @Override
    public String toString(){
        return "Profesor {id = " + id + ", nume = '" + nume + "', materie = '" + materie + "'}";
    }
    @Override
    public boolean equals(Object obj){
        if(this == obj)
            return true;
        if(!(obj instanceof Profesor))
            return false;
        Profesor p = (Profesor) obj;
        return id == p.id;
    }
    @Override
    public int hashCode(){
        return java.util.Objects.hash(id);
    }
}