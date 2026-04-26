package com.pao.proiect.catalog.model;

import java.util.*;

public class Clasa {
    private String nume;
    private List<Student> studenti = new ArrayList<>();
    public Clasa(String nume){
        this.nume = nume;
    }

    public String getNume(){
        return nume;
    }
    public void setNume(String nume){
        this.nume = nume;
    }
    public void adaugaStudent(Student student){
        studenti.add(student);
    }
    public List<Student> getStudenti(){
        return studenti;
    }

    @Override
    public String toString(){
        return "Clasa (nume = '" + nume + "', nr studenti: " + studenti.size() + ")";
    }
}