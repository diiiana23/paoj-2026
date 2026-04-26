package com.pao.proiect.catalog.model;

import java.util.*;

public class Student extends Persoana implements Comparable<Student> {
    private List<Nota> note = new ArrayList<>();
    public Student(int id, String nume){
        super(id, nume);
    }

    public void adaugaNota(Nota nota){
        note.add(nota);
    }
    public List<Nota> getNote(){
        return note;
    }
    public double calculeazaMedia(){
        if (note.isEmpty())
            return 0;
        return note.stream().mapToInt(Nota::getValoare).average().orElse(0);
    }

    @Override
    public String getRol() {
        return "Student";
    }
    @Override
    public int compareTo(Student s) {
        return Double.compare(s.calculeazaMedia(), this.calculeazaMedia());
    }
    @Override
    public String toString() {
        return "Student {id = " + id + ", nume = '" + nume + "'}";
    }
    @Override
    public boolean equals(Object obj) {
        if(this == obj)
            return true;
        if(!(obj instanceof Student))
            return false;
        Student s = (Student) obj;
        return id == s.id;
    }
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}