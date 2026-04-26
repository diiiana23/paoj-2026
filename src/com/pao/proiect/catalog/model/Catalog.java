package com.pao.proiect.catalog.model;

import java.util.*;

public class Catalog {
    private Map<Integer, Student> studenti = new HashMap<>();

    public void adaugaStudent(Student student){
        studenti.put(student.getId(), student);
    }
    public Student getStudent(int id){
        return studenti.get(id);
    }
    public void stergeStudent(int id){
        studenti.remove(id);
    }
    public List<Student> getTotiStudentii(){
        return new ArrayList<>(studenti.values());
    }

    @Override
    public String toString(){
        return "Catalog (nr studenti: " + studenti.size() + ")";
    }
}